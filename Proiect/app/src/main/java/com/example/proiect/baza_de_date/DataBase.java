package com.example.proiect.baza_de_date;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.proiect.baza_de_date.Place;
import com.example.proiect.baza_de_date.PlaceDao;

@Database(entities = {Place.class}, version = 1)
public abstract class DataBase extends RoomDatabase {

    private static DataBase instance;
    public abstract PlaceDao placeDao();

    public static synchronized DataBase getInstance(Context context) { //folosim o singura instanta a bazei de date in intreaga app
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            DataBase.class, "Places")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
