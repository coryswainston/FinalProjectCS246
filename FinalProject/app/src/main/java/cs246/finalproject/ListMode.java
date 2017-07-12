package cs246.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Schlottmann and Swainston
 * @version 1.0
 */

public class ListMode extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Entry> entries;
    private EntryAdapter adapter;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mode);

        SharedPreferences preferences = getSharedPreferences(MainActivity.USER_INFO, 0);
        username = preferences.getString(MainActivity.USERNAME, null);

        // populate the listView
        entries = new ArrayList<>();

        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference ref = fb.getReference(username);
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Entry entry = dataSnapshot.getValue(Entry.class);
                entries.add(entry);
                Log.d("ListMode", "added entry");
                Log.d("ListMode", String.valueOf(entries.size()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                entries.remove(dataSnapshot.getValue(Entry.class));
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Log.i("ENTRIES SIZE", String.valueOf(entries.size()));

        adapter = new EntryAdapter(this, entries);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Entry entry = (Entry) listView.getItemAtPosition(position);
                Intent intent = new Intent(ListMode.this, JournalMode.class);
                intent.putExtra(MainActivity.USERNAME, username);
                intent.putExtra(JournalMode.EXTRA_JOURNAL, entry.getJournalText());
                intent.putExtra(SurveyMode.EXTRA_RATING, entry.getRating());
                intent.putExtra(JournalMode.EXTRA_TIMESTAMP, entry.getTimestamp());

                startActivity(intent);
            }
        });
    }
}
