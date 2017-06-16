package cs246.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class SurveyMode extends AppCompatActivity {

    private TextView rating;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_mode);

        rating = (TextView)findViewById(R.id.rating);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        rating.setText(String.valueOf(seekBar.getProgress()));
    }

    void onSubmit(View view){
        
    }

    void onBack(View view){

    }
}
