package cs246.finalproject;

import android.content.Intent;
import android.icu.util.GregorianCalendar;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import java.util.Calendar;

/**
 * @author Schlottmann and Swainston
 * @version 1.0
 */

public class CalendarMode extends AppCompatActivity {

    public CalendarView calView;

    public static final String CALENDAR_FILTER_EXTRA = "calendar_filter_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_mode);

        calView = (CalendarView) findViewById(R.id.simpleCalendarView);
        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar c = Calendar.getInstance();
                c.set(year, month, dayOfMonth);
                String millis = String.valueOf(c.getTimeInMillis());
                Log.d("CalendarMode", "filter is : " + millis);
                Intent intent = new Intent(CalendarMode.this, ListMode.class);
                intent.putExtra(CALENDAR_FILTER_EXTRA, millis);

                startActivity(intent);
            }
        });
    }
}
