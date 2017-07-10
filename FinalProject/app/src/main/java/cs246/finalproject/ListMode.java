package cs246.finalproject;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private List<Entry> entries;
    private ListAdapter adapter;
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
                entries.add(dataSnapshot.getValue(Entry.class));
                Log.d("ListMode", "added entry");
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

        adapter = new ArrayAdapter<Entry>(this, R.layout.support_simple_spinner_dropdown_item, entries);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}
