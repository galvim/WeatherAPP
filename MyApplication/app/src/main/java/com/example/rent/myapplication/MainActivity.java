package com.example.rent.myapplication;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.NotificationCompat;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.PendingIntent.FLAG_CANCEL_CURRENT;
import static android.app.PendingIntent.FLAG_NO_CREATE;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.temperature)
    TextView temperature;
    @BindView(R.id.image_view)
    ImageView weatherIcon;
    @BindView(R.id.sky_text)
    TextView sky_text;
    @BindView(R.id.city_name)
    EditText edit_text;

    private Retrofit retrofit;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://weathers.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        search("Warszawa");
    }

    private void search(String citySearch) {

        WeatherService weatherService = retrofit.create(WeatherService.class);
        weatherService.getWeather(citySearch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataContainer -> {
                    WeatherDetail weatherDetail = dataContainer.getData();
                    city.setText(weatherDetail.getLocation());
                    temperature.setText(weatherDetail.getTemperature() + "\u00b0");
                    sky_text.setText(weatherDetail.getDescription());

                    showImageByTemperature(weatherDetail.getDescription());

                    if (progressDialog != null) {
                        progressDialog.hide();
                    }
                    showNotification(citySearch);
                });
    }
    private void showNotification(String citySearch){
        PendingIntent pendingIntent = PendingIntent.getActivity(this,11 , new Intent(this,MainActivity.class), FLAG_CANCEL_CURRENT);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.pada);
      Notification notification =  new NotificationCompat.Builder(this)
                .setContentText("Załadowano informacje pogodowe dla miasta" + citySearch)
              .setSmallIcon(R.drawable.pada)
              .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap)
                        .setBigContentTitle("BIG CONTENT VIEW").setSummaryText("summury text"))
              .addAction(R.drawable.ic_search,"Search",pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(citySearch.hashCode(),notification);

    SimpleModel simpleModel = new SimpleModel("marian","janasik","poznan","88");

        SimpleModel secondSimpleModel = new SimpleModel.Builder()
                .withName("Michal")
                .withSurname("Ners")
                .withAddress("Wuwua")
                .withPhone("666")
                .build();

        MyObservable myObservable = new MyObservable();
        myObservable.subscribe(new MailObserver());
        myObservable.subscribe(new MailObserver());
        myObservable.notifyAllSubscribers();
    }

    @OnClick(R.id.search_butto)
    void onSearchButtonClick() {
        progressDialog = ProgressDialog.show(this, "ładowanko", "się ładuje", true);
        search(edit_text.getText().toString());

    }

    private void showImageByTemperature(String description) {
        if ("Sky is clear".equalsIgnoreCase(description)) {
            weatherIcon.setImageResource(R.drawable.pobrane);
        } else if ("Few clouds".equalsIgnoreCase(description)) {
            weatherIcon.setImageResource(R.drawable.pochmurno);
        } else {
            weatherIcon.setImageResource(R.drawable.pada);
        }
    }
}
