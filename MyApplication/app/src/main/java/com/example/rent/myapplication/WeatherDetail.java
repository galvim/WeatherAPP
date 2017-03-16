package com.example.rent.myapplication;

import com.google.gson.annotations.SerializedName;

public class WeatherDetail {


    private String location;

    private String temperature;

    private String skytext;

    private String humidity;

    private String wind;

    private String date;

    private String day;

     // NIE MUSIMY DODAWAC SERIALIZED NAME BO WSZYSTKO Z MALEJ !!!!!
    public String getLocation() {
        return location;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return skytext;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWind() {
        return wind;
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }
}
