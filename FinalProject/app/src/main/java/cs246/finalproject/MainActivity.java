package cs246.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * MainActivity
 *
 * Pull dis bizness
 * Don't tell me what to do
 *
 * @author Schlottmann and Swainston
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;

    public static final String USERNAME = "username";
    public static final String USER_INFO = "userInfo";

    private String name;

    /**
     * Checking for a saved username. If there is no username we will create one.
     * @param savedInstanceState It's another saved instance state. Maybe Alabama, possibly mississippi. One of those.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(USER_INFO, 0);
        Log.i("MainActivity", "Got shared preferences");
        String username = preferences.getString(USERNAME, null);
        Log.i("MainActivity", "Got username");
        if(username == null){
            Intent intent = new Intent(this, SettingsMode.class);
            startActivity(intent);
        }

        name = username;
        setTitle(name);
    }

    public boolean openSurvey(View view){
        Intent intent = new Intent(this, SurveyMode.class);
        intent.putExtra(USERNAME, name);
        try {
            startActivity(intent);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean openCalendar(){
        Intent intent = new Intent(this, CalendarMode.class);
        try {
            startActivity(intent);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean openList(){
        Intent intent = new Intent(this, ListMode.class);
        try {
            startActivity(intent);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean openJournal(){
        Intent intent = new Intent(this, JournalMode.class);
        try {
            startActivity(intent);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
