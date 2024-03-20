package com.example.ex1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.BatteryManager;
import android.widget.Toast;

public class MyBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent)
    {

        if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction()))
        {
            Toast.makeText(context, "Bateria s a schimbat", Toast.LENGTH_SHORT).show();
        }

       if (Intent.ACTION_BATTERY_LOW.equals(intent.getAction()))
       {
           int levelBaterie = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);

           //verificam daca nivelul bateriei e scazut
           if (levelBaterie < 20)
           {
               Toast.makeText(context, "Bateria e 20%", Toast.LENGTH_SHORT).show();
           }
       }

    }
}
