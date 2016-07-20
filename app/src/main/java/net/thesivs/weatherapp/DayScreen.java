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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DayScreen extends AppCompatActivity {
    private WeatherData myWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_screen);

        myWeather = new WeatherData();

        String zipCode = "94577";
        String url_preZip = "http://api.openweathermap.org/data/2.5/weather?zip=";
        String url_postZip = "&units=imperial&appid=c15dc5a78ba5919b373dfb1d589d6b2d";

        URL url = null;
        try {
            url = new URL(url_preZip + zipCode + url_postZip);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        new WeatherDataAsyncTask().execute(url);
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

    public class WeatherDataAsyncTask extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... params) {
            HttpURLConnection httpURLConnection = null;
            StringBuilder stringBuilder = new StringBuilder();

            try {
                URL url = params[0];
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");

                if(httpURLConnection.getResponseCode() == 200) {
                    InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(httpURLConnection != null )
                    httpURLConnection.disconnect();
            }

            Log.e("values", stringBuilder.toString());
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject jsonObject = new JSONObject(result);

                JSONArray weatherArray = new JSONArray(); //[0].main, description, icon
                JSONObject mainObject = new JSONObject(); //temp, pressure, humitidy, temp_min, temp_max
                JSONObject sysObject = new JSONObject(); //sunrise, sunset
                JSONObject rainObject = new JSONObject(); //rain_3h

                weatherArray = jsonObject.getJSONArray("weather");
                mainObject = jsonObject.getJSONObject("main");
                sysObject = jsonObject.getJSONObject("sys");

                /* ALL OBJECTS/ARRAYS DEFINED, TIME TO SET myWeather. variables */
                if(jsonObject.isNull("rain")) {
                    rainObject = null;
                    myWeather.setRain_3h("0");
                }
                else {
                    rainObject = jsonObject.getJSONObject("rain");
                    myWeather.setRain_3h(rainObject.getString("rain_3h").toString());
                }

                myWeather.setDt(jsonObject.getString("dt").toString());
                myWeather.setCity_name(jsonObject.getString("name").toString());

                myWeather.setWeather_description(weatherArray.getJSONObject(0).getString("description").toString());
                myWeather.setWeather_main(weatherArray.getJSONObject(0).getString("main").toString());
                myWeather.setWeather_icon(weatherArray.getJSONObject(0).getString("icon").toString());

                myWeather.setMain_temp(mainObject.getString("temp").toString());
                myWeather.setMain_temp_high(mainObject.getString("temp_max").toString());
                myWeather.setMain_temp_min(mainObject.getString("temp_min").toString());
                myWeather.setMain_humidity(mainObject.getString("humidity").toString());

                myWeather.setSys_sunrise(sysObject.getString("sunrise").toString());
                myWeather.setSys_sunset(sysObject.getString("sunset").toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

