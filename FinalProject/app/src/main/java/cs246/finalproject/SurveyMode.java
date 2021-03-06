package cs246.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import junit.framework.Assert;

/**
 * Rate heartburn on a scale of 0 - 10
 *
 * @author Schlottmann and Swainston
 * @version 1.0
 */
public class SurveyMode extends AppCompatActivity {

    public TextView rating;
    private SeekBar seekBar;
    private Entry entry;
    private String username;

    public static String EXTRA_RATING = "extra_rating";

    /**
     * Find the seekbar.
     * Set listener to update the box whenever the progress changes.
     * Get any passed extras
     *
     * @param savedInstanceState It's a saved instance state probably
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_mode);

        // get intent to see if we're editing an existing entry
        Intent intent = getIntent();
        username = intent.getStringExtra(MainActivity.USERNAME);
        entry = new Entry();
        String journalExtra = intent.getStringExtra(JournalMode.EXTRA_JOURNAL);
        if (journalExtra != null){
            entry.setJournalText(journalExtra);
            entry.setTimestamp(intent.getStringExtra(JournalMode.EXTRA_TIMESTAMP));
            entry.setTitle(intent.getStringExtra(JournalMode.EXTRA_TITLE));
        }

        // find our seekbar
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        rating = (TextView)findViewById(R.id.rating);
        rating.setText(String.valueOf(seekBar.getProgress()));

        // set a listener to update the box whenever the progress changes
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("SurveyMode", "Progress bar changed");
                rating.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // auto generated stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // auto generated stub
            }
        });
    }

    /**
     * Sending entry to database
     * @param view It's a view. A nice one. Overlooking the water. Cost a lot of money to get this view.
     */
    void onSubmit(View view){
        // create an Entry object
        entry.setRating(seekBar.getProgress());
        Log.d("SurveyMode:onSubmit", "created entry");

        // get a database instance
        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        Log.d("SurveyMode:onSubmit", "got database instance");
        DatabaseReference fbReference = fb.getReference(username);
        Log.d("SurveyMode:onSubmit", "got username reference");
        fbReference = fbReference.child(entry.getTimestamp());

        // save the entry
        fbReference.setValue(entry);
        Log.d("SurveyMode:onSubmit", "set entry");
        Toast.makeText(this, "Entry saved!", Toast.LENGTH_SHORT).show();

        // go to list mode
        Intent intent = new Intent(this, ListMode.class);
        startActivity(intent);
    }

    void onBack(View view){
        finish();
    }

    void addJournal(View view){
        Intent intent = new Intent(this, JournalMode.class);
        intent.putExtra(EXTRA_RATING, seekBar.getProgress());
        intent.putExtra(MainActivity.USERNAME, username);
        intent.putExtra(MainActivity.ACTIVITY_NAME, "SurveyMode");


        startActivity(intent);
    }
}
