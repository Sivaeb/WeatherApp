package net.thesivs.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.*;

public class DayScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_screen);

        /* */
        //String input2 = readWeatherAPI();
        String input = "http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=c15dc5a78ba5919b373dfb1d589d6b2d";
        try {
            JSONObject jsonObject = new JSONObject(input);
            Log.i("A:", jsonObject.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_day_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case (R.id.menu_close):
                finish();
                return true;
            case (R.id.menu_week):
                Intent intent = new Intent(getApplicationContext(), WeekScreen.class);
                startActivity(intent);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
