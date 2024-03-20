package com.example.ex4;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
    private final IBinder binder = new LocalBinder();
    String nume, prenume;
    double scor;
    int probleme;
    Persoane persoana;

    public MyService(Persoane persoana) {
        this.persoana = persoana;
    }

    public class LocalBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    @Override
    public void onCreate() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public boolean verificareProbleme() {
        if (persoana.getProbleme() < 10) {
            return false;
        } else {
            return true;
        }
    }
}
