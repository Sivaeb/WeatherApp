package net.thesivs.weatherapp;

/**
 * Created by Sivaeb on 7/11/2016.
 * JSON response will be used to populate below. This only works for current weather
 * Can perhaps create an array of WeatherData for each 'data' set we wish to populate.
 * In other words, for week we may need 5-7 of these and only certains fields are needed.
 */

public class WeatherData {
    private String city_name;
    private String dt;
    private String main_temp;
    private String main_temp_min;
    private String main_temp_high;
    private String main_humidity;
    private String weather_icon;
    private String weather_main;
    private String weather_description;
    private String sys_sunrise;
    private String sys_sunset;
    private String rain_3h;

    //Everything set to null incase a variable is not used (i.e., week view).
    public WeatherData() {
        this.city_name = null;
        this.dt = null;
        this.main_temp = null;
        this.main_temp_min = null;
        this.main_temp_high = null;
        this.main_humidity = null;
        this.weather_icon = null;
        this.weather_main = null;
        this.weather_description = null;
        this.sys_sunrise = null;
        this.sys_sunset = null;
        this.rain_3h = null;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getMain_temp() {
        return main_temp;
    }

    public void setMain_temp(String main_temp) {
        this.main_temp = main_temp;
    }

    public String getMain_temp_min() {
        return main_temp_min;
    }

    public void setMain_temp_min(String main_temp_min) {
        this.main_temp_min = main_temp_min;
    }

    public String getMain_temp_high() {
        return main_temp_high;
    }

    public void setMain_temp_high(String main_temp_high) {
        this.main_temp_high = main_temp_high;
    }

    public String getMain_humidity() {
        return main_humidity;
    }

    public void setMain_humidity(String main_humidity) {
        this.main_humidity = main_humidity;
    }

    public String getWeather_icon() {
        return weather_icon;
    }

    public void setWeather_icon(String weather_icon) {
        this.weather_icon = weather_icon;
    }

    public String getWeather_main() {
        return weather_main;
    }

    public void setWeather_main(String weather_main) {
        this.weather_main = weather_main;
    }

    public String getWeather_description() {
        return weather_description;
    }

    public void setWeather_description(String weather_description) {
        this.weather_description = weather_description;
    }

    public String getSys_sunrise() {
        return sys_sunrise;
    }

    public void setSys_sunrise(String sys_sunrise) {
        this.sys_sunrise = sys_sunrise;
    }

    public String getSys_sunset() {
        return sys_sunset;
    }

    public void setSys_sunset(String sys_sunset) {
        this.sys_sunset = sys_sunset;
    }

    public String getRain_3h() {
        return rain_3h;
    }

    public void setRain_3h(String rain_3h) {
        this.rain_3h = rain_3h;
    }
}
