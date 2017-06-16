package cs246.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsMode extends AppCompatActivity {

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_mode);

        preferences = getSharedPreferences(MainActivity.USER_INFO, 0);
    }

    void onSubmit(View view){
        EditText editText = (EditText) findViewById(R.id.editText);
        String username = editText.getText().toString();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(MainActivity.USERNAME, username);
        editor.apply();
        Toast.makeText(this, "Name saved!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
