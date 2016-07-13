package net.thesivs.weatherapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DayScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_screen);

        String zipCode = "94577";
        String url_preZip = "http://api.openweathermap.org/data/2.5/weather?zip=";
        String url_postZip = "&units=imperial&appid=c15dc5a78ba5919b373dfb1d589d6b2d";
        new WeatherDataAsyncTask().execute(url_preZip + zipCode + url_postZip);
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

    public class WeatherDataAsyncTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {
            HttpURLConnection httpURLConnection = null;

            try {
                URL url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                String input = new BufferedInputStream(httpURLConnection.getInputStream()).toString();

                if(httpURLConnection.getResponseCode() == 200) {
                    JSONObject jsonObject = new JSONObject(input);
                    jsonObject.get("name");
                    jsonObject.getJSONArray("weather"); //weather[0].temp...
                    jsonObject.getJSONObject("sys"); //sys.get("dt")...

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                httpURLConnection.disconnect();
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }
}
