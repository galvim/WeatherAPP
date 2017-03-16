package com.example.rent.myapplication;

import android.util.Log;

/**
 * Created by RENT on 2017-03-16.
 */

public class MailObserver implements Observer {
    @Override
    public void notifyMe() {
        Log.d("result","OOOo nowy odcinek Ucha Prezesa");
    }
}
