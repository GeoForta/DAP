package com.example.ex4;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private final IBinder binder = new LocalBinder();
    String nume, prenume;
    double scor;
    int probleme;
    Persoane persoana;
    Persoane persoana2;

    public MyService(Persoane persoana, Persoane persoana2) {
        this.persoana = persoana;
        this.persoana2 = persoana2;
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
        Log.i("Persoane", "pers1 " + persoana.getProbleme());
        if (persoana.getProbleme() == 10)
            return true;
        return false;
    }
    public boolean verificareProbleme2() {
        Log.i("Persoane", "pers2 " + persoana2.getProbleme());
        if (persoana2.getProbleme() == 10) {
            return true;
        }
        return false;
    }

    public boolean filtScor()
    {
        if (persoana.getScor() > persoana2.getScor())
            return true;
        return false;
    }
}
