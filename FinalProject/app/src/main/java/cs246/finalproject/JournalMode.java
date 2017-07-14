package cs246.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * @author Schlottmann and Swainston
 * @version 1.0
 */

public class JournalMode extends AppCompatActivity {

    private Entry entry;
    private String username;
    private TextView textBox;
    private EditText titleBox;

    public static String EXTRA_JOURNAL = "extra_journal";
    public static String EXTRA_TIMESTAMP = "extra_timestamp";
    public static String EXTRA_TITLE = "extra_title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_mode);

        Intent intent = getIntent();
        username = intent.getStringExtra(MainActivity.USERNAME);
        entry = new Entry();
        entry.setRating(intent.getIntExtra(SurveyMode.EXTRA_RATING, -1));

        textBox = (MultiAutoCompleteTextView) findViewById(R.id.textBox);
        titleBox = (EditText) findViewById(R.id.title);

        String journalExtra = intent.getStringExtra(EXTRA_JOURNAL);
        if (journalExtra != null){
            textBox.setText(journalExtra);
            entry.setTimestamp(intent.getStringExtra(EXTRA_TIMESTAMP));
            entry.setTitle(intent.getStringExtra(EXTRA_TITLE));
            titleBox.setText(entry.getTitle());
        }
    }

    /**
     * submit the entry to the database
     */
    void onSubmit(View view){
        String journal = textBox.getText().toString();
        String title = titleBox.getText().toString();
        entry.setJournalText(journal);
        entry.setTitle(title);
        Log.d("JournalMode", "Created entry");

        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference fbReference = fb.getReference(username);
        fbReference = fbReference.child(entry.getTimestamp());
        Log.d("JournalMode", "got reference");

        fbReference.setValue(entry);
        Toast.makeText(this, "Entry saved!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, ListMode.class);
        startActivity(intent);
    }

    void onBack(View view){
        finish();
    }

    void onDelete(View view) {
        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference fbReference = fb.getReference(username);
        fbReference = fbReference.child(entry.getTimestamp());
        Log.d("JournalMode: onDelete", "entry" + fbReference.getKey());
        fbReference.removeValue();
        Toast.makeText(this, "Entry deleted", Toast.LENGTH_SHORT).show();
        finish();
    }

    void addRating(View view){
        Intent intent = new Intent(this, SurveyMode.class);
        intent.putExtra(MainActivity.ACTIVITY_NAME, "JournalMode");
        intent.putExtra(EXTRA_JOURNAL, textBox.getText().toString());
        intent.putExtra(MainActivity.USERNAME, username);
        intent.putExtra(JournalMode.EXTRA_TITLE, titleBox.getText().toString());
        intent.putExtra(JournalMode.EXTRA_TIMESTAMP, entry.getTimestamp());

        startActivity(intent);
    }
}
