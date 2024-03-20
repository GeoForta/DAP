package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.content.BroadcastReceiver;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // schimbarile bateriei
        IntentFilter batteryChangedIntentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        MyBroadcast batteryChangedReceiver = new MyBroadcast();
        registerReceiver(batteryChangedReceiver, batteryChangedIntentFilter);

        // nivelul bateriei
        IntentFilter batteryLowIntentFilter = new IntentFilter(Intent.ACTION_BATTERY_LOW);
        MyBroadcast batteryLowReceiver = new MyBroadcast();
        registerReceiver(batteryLowReceiver, batteryLowIntentFilter);






    }
}