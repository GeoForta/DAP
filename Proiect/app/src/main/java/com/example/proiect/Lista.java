package com.example.proiect;

import android.location.Location;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class Lista extends Application{

    private static Lista singleton;
    private List<Location> myLocations;

    public List<Location> getMyLocations() {
        return myLocations;
    }
    public Lista getInstance()
    {
        return singleton;
    }
    public void onCreate()
    {
        super.onCreate();
        singleton = this;
        myLocations = new ArrayList<>();


    }
}
