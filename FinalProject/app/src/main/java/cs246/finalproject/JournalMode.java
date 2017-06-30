package cs246.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_mode);

        Intent intent = getIntent();
        username = intent.getStringExtra(MainActivity.USERNAME);

        textBox = (MultiAutoCompleteTextView) findViewById(R.id.textBox);
    }

    /**
     * submit the entry to the database
     */
    void onSubmit(View view){
        String journal = textBox.getText().toString();
        entry = new Entry(-1, journal);
        Log.d("JournalMode", "Created entry");

        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference fbReference = fb.getReference(username);
        fbReference = fbReference.child(entry.getTimestamp());
        Log.d("JournalMode", "got reference");

        fbReference.setValue(entry);
        Toast.makeText(this, "Entry saved!", Toast.LENGTH_SHORT).show();
        finish();
    }

    void onBack(View view){
        finish();
    }

    void onDelete(View view) {
        if (entry == null) {
            Log.d("JournalMode:onDelete", "entry is null");
            Toast.makeText(this, "Entry is not created", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d("JournalMode:onDelete", "entry is not null");

        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference fbReference = fb.getReference(username);
        fbReference = fbReference.child(entry.getTimestamp());
        fbReference.removeValue();
        Toast.makeText(this, "Entry deleted", Toast.LENGTH_SHORT).show();
        finish();
    }
}
