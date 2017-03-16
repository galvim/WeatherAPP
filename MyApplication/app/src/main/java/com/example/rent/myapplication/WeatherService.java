package com.example.rent.myapplication;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("api.php")
    Observable<DataContainer> getWeather(@Query("city") String city);
}
