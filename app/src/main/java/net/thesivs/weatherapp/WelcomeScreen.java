package net.thesivs.weatherapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity {
    private Button weekButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        weekButton = (Button) findViewById(R.id.button);
        weekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DayScreen.class);
                startActivity(intent);
            }
        });

    }

}
