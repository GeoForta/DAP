package com.example.proiect;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ShowSavedLocations extends AppCompatActivity {

    ListView lv_savedLocations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_saved_locations);

        lv_savedLocations = findViewById(R.id.lv_waypoints);

        Lista lista = (Lista)getApplicationContext();
        List<Location>savedLocations = lista.getMyLocations();

        lv_savedLocations.setAdapter(new ArrayAdapter<Location>(this, android.R.layout.simple_list_item_1, savedLocations));



    }
}