package com.example.rent.myapplication;

import android.util.Log;

/**
 * Created by RENT on 2017-03-16.
 */

public class DisplayFirstNameCommand implements Command{
    @Override
    public void execute() {
        Log.d("result","Majkel Ners");
    }
}
