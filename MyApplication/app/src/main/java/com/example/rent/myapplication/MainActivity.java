package com.example.rent.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.temperature)
    TextView  temperature;
    @BindView(R.id.image_view)
    ImageView weatherIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://weathers.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        WeatherService weatherService = retrofit.create(WeatherService.class);
        weatherService.getWeather("Warszawa")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataContainer -> {
                    WeatherList weatherList = dataContainer.getData();
                    city.setText(weatherList.getLocation());
                    temperature.setText(weatherList.getTemperature());


                });


    }
}
