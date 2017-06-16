package cs246.finalproject;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class SettingsMode extends AppCompatActivity {

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_mode);

        preferences = getSharedPreferences(MainActivity.USER_INFO, 0);
    }

    void onSubmit(){
        EditText editText = (EditText) findViewById(R.id.editText);
        String username = editText.getText().toString();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(MainActivity.USERNAME, username);
        editor.apply();
    }
}
