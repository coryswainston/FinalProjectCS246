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
import java.util.Comparator;
import java.util.Iterator;
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
    private String filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mode);

        SharedPreferences preferences = getSharedPreferences(MainActivity.USER_INFO, 0);
        username = preferences.getString(MainActivity.USERNAME, null);

        filter = getIntent().getStringExtra(CalendarMode.CALENDAR_FILTER_EXTRA);
        if (filter == null){
            filter = "";
        }
        Log.d("ListMode", "Filter is: " + filter);
        // populate the listView
        entries = new ArrayList<>();

        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference ref = fb.getReference(username);
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // add an entry to the array
                Entry entry = dataSnapshot.getValue(Entry.class);
                entries.add(entry);
                Log.d("ListMode", "added entry");
                Log.d("ListMode", String.valueOf(entries.size()));

                // sort from newest to oldest
                //noinspection Since15
                entries.sort(new Comparator<Entry>() {
                    @Override
                    public int compare(Entry o1, Entry o2) {
                        long t1 = Long.parseLong(o1.getTimestamp());
                        long t2 = Long.parseLong(o2.getTimestamp());
                        if (t1 > t2){
                            return -1;
                        } else {
                            return 1;
                        }
                    }
                });

                adapter.getFilter().filter(filter);

                Log.d("ListMode", "Entries size: " + String.valueOf(entries.size()));
                Log.d("Adapter", "Entries size: " + String.valueOf(adapter.getCount()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Entry entry = dataSnapshot.getValue(Entry.class);
                for (Entry e : entries) {
                    if (e.getTimestamp().equals(entry.getTimestamp())){
                        e = entry;
                    }
                }
                adapter.getFilter().filter(filter);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d("childRemoved", String.valueOf(entries.size()));
                Entry entry = dataSnapshot.getValue(Entry.class);
                Iterator<Entry> i = entries.iterator();
                while (i.hasNext()){
                    Entry e = i.next();
                    if (e.getTimestamp().equals(entry.getTimestamp())){
                        i.remove();
                        break;
                    }
                }

                Log.d("childRemoved", String.valueOf(entries.size()));

                adapter.getFilter().filter(filter);

                adapter.notifyDataSetChanged();
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
                Log.d("clickListener", entry.getTimestamp());
                Intent intent = new Intent(ListMode.this, JournalMode.class);
                intent.putExtra(MainActivity.USERNAME, username);
                intent.putExtra(JournalMode.EXTRA_JOURNAL, entry.getJournalText());
                intent.putExtra(JournalMode.EXTRA_TITLE, entry.getTitle());
                intent.putExtra(SurveyMode.EXTRA_RATING, entry.getRating());
                intent.putExtra(JournalMode.EXTRA_TIMESTAMP, entry.getTimestamp());

                startActivity(intent);
            }
        });
    }

    void openCalendar(View view){
        Intent intent = new Intent(this, CalendarMode.class);
        startActivity(intent);
    }

    void openJournal(View view) {
        Intent intent = new Intent(this, JournalMode.class);
        intent.putExtra(MainActivity.USERNAME, username);
        startActivity(intent);
    }

    void removeFilter(View view){
        filter = "";
        adapter.getFilter().filter(filter);
    }
}
