package com.example.rent.myapplication;

import com.google.gson.annotations.SerializedName;

public class WeatherList {


    private String location;

    private String temperature;

    private String description;

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
        return description;
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
