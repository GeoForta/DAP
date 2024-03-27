package com.example.proiect.baza_de_date;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface PlaceDao {
    @Query("SELECT * FROM places")
    List<Place> getAll();

    @Insert
    void insert(Place place);
}