package com.example.proiect;

import androidx.annotation.NonNull;
import androidx.annotation.Px;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.os.LocaleList;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import android.Manifest;
import android.widget.Toast;

import java.util.List;


public class Gps extends AppCompatActivity {

    private static final int PERMISSIONS_FINE_LOCATION = 99;
    TextView tv_lat, tv_lon, tv_altitude, tv_accuracy, tv_speed, tv_sensor, tv_update, tv_adress, tv_wayPointCounts;
    Button btn_newWaypoint, btn_showWaypoint, btn_showMap, back;

    //google s api for location services
    FusedLocationProviderClient fusedLocationProviderClient;

    Location currentLocation;
    List<Location> savedLocations;
    boolean updateOn = false;

    //location request
    LocationRequest locationRequest;
    LocationCallback locationCallBack;

    Switch sw_locationUpdated, sw_gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        tv_lat = findViewById(R.id.tv_lat);
        tv_lon = findViewById(R.id.tv_lon);
        tv_altitude = findViewById(R.id.tv_altitude);
        tv_accuracy = findViewById(R.id.tv_accuracy);
        tv_speed = findViewById(R.id.tv_speed);
        tv_sensor = findViewById(R.id.tv_sensor);
        tv_update = findViewById(R.id.tv_updates);
        tv_adress = findViewById(R.id.tv_address);
        sw_gps = findViewById(R.id.sw_gps);
        sw_locationUpdated = findViewById(R.id.sw_locationsupdates);
        btn_newWaypoint = findViewById(R.id.btn_newWayPoint);
        btn_showWaypoint = findViewById(R.id.btn_showWayPointList);
        tv_wayPointCounts = findViewById(R.id.tv_countOfCrumbs);
        btn_showMap = findViewById(R.id.btn_showMap);
        back = findViewById(R.id.back);

        //set all the properties of location request
        locationRequest = new LocationRequest();
        //cat de des se actualizeaza
        locationRequest.setInterval(1000 * 30);

        locationRequest.setFastestInterval(1000 * 5);

        locationRequest.setPriority(locationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        //event is triggered whenever the update interval is met
        locationCallBack = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                //save the location
                Location location = locationResult.getLastLocation();
                updateUIValues(location);
            }
        };
        btn_newWaypoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the gps location
                //add the new location to the global list
                Lista lista = (Lista) getApplicationContext();
                savedLocations = lista.getMyLocations();
                savedLocations.add(currentLocation);
            }
        });
        btn_showMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Gps.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        btn_showWaypoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Gps.this, ShowSavedLocations.class);
                startActivity(intent);
            }
        });

        sw_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sw_gps.isChecked()) {
                    //most accurate - use gps
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    tv_sensor.setText("Using GPS sensors");
                } else {
                    locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                    tv_sensor.setText("Using Towers + WIFI");
                }
            }
        });

        sw_locationUpdated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sw_locationUpdated.isChecked()) {
                    //turn on location tracking
                    startLocationUpdates();
                } else {
                    //turn off tracking
                    stopLocationUpdates();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLocation != null) {
                    Intent intent = new Intent(Gps.this, MainActivity.class);
                    double latitudine = currentLocation.getLatitude();
                    double longitudine = currentLocation.getLongitude();
                    intent.putExtra("latitudine", latitudine);
                    intent.putExtra("longitudine", longitudine);
                    startActivity(intent);
                } else {
                    Toast.makeText(Gps.this, "Current location is not available", Toast.LENGTH_SHORT).show();
                }
            }
        });


        updateGPS();
    }

    private void stopLocationUpdates() {
        tv_update.setText("Location is not being tracked");
        tv_lat.setText("Not tracking location");
        tv_lon.setText("Not tracking location");
        tv_speed.setText("Not tracking location");
        tv_adress.setText("Not tracking location");
        tv_accuracy.setText("Not tracking location");
        tv_altitude.setText("Not tracking location");
        tv_sensor.setText("Not tracking location");

        fusedLocationProviderClient.removeLocationUpdates(locationCallBack);
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            tv_update.setText("Location is being tracked");
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallBack, null);
            updateGPS();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    updateGPS();
                } else {
                    Toast.makeText(this, "This app requires permission", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }

    }

    private void updateGPS() {
        //get permissions from the users
        //get the current location
        //update the UI

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(Gps.this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    //avem permisiunea. punem valorile locatiei
                    updateUIValues(location);
                    currentLocation = location;

                }
            });
        } else {
            //inca nu avem permisiunea
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_FINE_LOCATION);
            }

        }
    }

    private void updateUIValues(Location location) {
        if (location != null) {
            // Continua actualizarea interfeței utilizator cu valorile locației
            tv_lat.setText(String.valueOf(location.getLatitude()));
            tv_lon.setText(String.valueOf(location.getLongitude()));
            tv_accuracy.setText(String.valueOf(location.getAccuracy()));

            if (location.hasAltitude()) {
                tv_altitude.setText(String.valueOf(location.getAltitude()));
            } else {
                tv_altitude.setText("Not available");
            }

            if (location.hasSpeed()) {
                tv_speed.setText(String.valueOf(location.getSpeed()));
            } else {
                tv_speed.setText("Not available");
            }

            Geocoder geocoder = new Geocoder(Gps.this);
            try {
                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if (!addresses.isEmpty()) {
                    tv_adress.setText(addresses.get(0).getAddressLine(0));
                } else {
                    tv_adress.setText("Address not found");
                }
            } catch (Exception e) {
                tv_adress.setText("Unable to get street address");
            }

            Lista lista = (Lista) getApplicationContext();
            savedLocations = lista.getMyLocations();
            tv_wayPointCounts.setText(String.valueOf(savedLocations.size()));
        } else {
            tv_lat.setText("Location not available");
            tv_lon.setText("Location not available");
            tv_accuracy.setText("Location not available");
            tv_altitude.setText("Location not available");
            tv_speed.setText("Location not available");
            tv_adress.setText("Location not available");
            tv_wayPointCounts.setText("Location not available");
        }
    }
}


