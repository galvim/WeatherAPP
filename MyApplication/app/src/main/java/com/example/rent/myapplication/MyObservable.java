package com.example.rent.myapplication;

import java.util.ArrayList;
import java.util.List;

public class MyObservable implements Observable {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void subscribe(Observer observer) {

        observers.add(observer);
    }

    public void notifyAllSubscribers() {
        for (Observer observer : observers) {
            observer.notifyMe();
        }
    }
}
