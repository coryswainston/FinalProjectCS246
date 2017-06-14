package cs246.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
//test change
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //this is a change
    }

    public boolean openSurvey(View view){
        Intent intent = new Intent(this, SurveyMode.class);
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
