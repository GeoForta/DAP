package com.example.proiect;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class MyService extends Service {

    String[] defectiuni;
    private final IBinder binder = new LocalBinder();
    public MyService() {
    }

    public class LocalBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public String defectiune()
    {
        defectiuni = new String[3];
        defectiuni[0] = "Am facut accident. Cheama ajutoare";
        defectiuni[1] = "Mi s a spart roata. Cheama ajutoare";
        defectiuni[2] = "Am ramas fara motorina, nu am unde sa umplu.";

        Random random = new Random();
        int indexAleatoriu = random.nextInt(defectiuni.length);
        String mesajDefectiune = defectiuni[indexAleatoriu];

        return mesajDefectiune;
    }
}