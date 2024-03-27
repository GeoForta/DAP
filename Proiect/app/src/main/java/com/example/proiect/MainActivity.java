package com.example.proiect;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.NoCopySpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.Manifest;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.Random;

import com.example.proiect.baza_de_date.DataBase;
import com.example.proiect.baza_de_date.PlaceDao;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import java.util.Arrays;
import java.util.List;





public class MainActivity extends AppCompatActivity {


    Weather weatherResponse;
    private String telefonMama, telefonTata, telefonSora;
    private PlacesClient placesClient;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final String BASE_URL = "https://api.openweathermap.org/";
    private static final String API_KEY = "b68bb023cc94df6498d02cd24ee7fba4";
    private static final int REQUEST_CODE = 1;
    String[] defectiuni;
    String msj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBase db = DataBase.getInstance(getApplicationContext());

        PlaceDao placeDao = db.placeDao();



        Button buton = findViewById(R.id.trimitere);

        MyService myService = new MyService();
        msj = myService.defectiune();
        Log.e("MESAJ EROARE", msj);

        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Gps.class);
                startActivity(intent);
            }
        });


//----------------------------------------------------------------------------
        //solicitam permisiunea pentru trimitere mesaj
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    PERMISSION_REQUEST_CODE);
        } else {
            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }

        //creem persoanele
        Contacte persoana1 = new Contacte("Mama", "Moldovan", "0757411782");
        Contacte persoana2 = new Contacte("Tata", "Grigore", "0787548519");
        Contacte persoana3 = new Contacte("Sora", "Moldovan", "0787548517");

        telefonMama = persoana1.getNumarTelefon();
        telefonTata = persoana2.getNumarTelefon();
        telefonSora = persoana3.getNumarTelefon();



        Button mesaj = findViewById(R.id.mesaj);
        mesaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                if (intent != null && weatherResponse != null) {
                    double latitudine = intent.getDoubleExtra("latitudine", 0.0);
                    double longitudine = intent.getDoubleExtra("longitudine", 0.0);
                    double temperatura = weatherResponse.getMain().getTemp();
                    String msg = "Latitudine: " + latitudine + "\nLongitudine: " + longitudine + "\nTemperatura: " + temperatura + msj;
                    sendMessage(telefonMama, msg);
                    sendMessage(telefonTata, msg);
                    sendMessage(telefonSora, msg);
                    isClose();
                } else {
                    Toast.makeText(getApplicationContext(), "This intent is null", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button vreme = findViewById(R.id.weather);
        vreme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = "Bistrita";
                if (!city.isEmpty()) {
                    getWeather(MainActivity.this, city);
                } else {
                    Toast.makeText(MainActivity.this, "enter a city name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //------------------------------------- // places
        Places.initialize(getApplicationContext(), getString(R.string.google_places_api_key));
        placesClient = Places.createClient(this);

        // Creeaza cererea pentru a gasi locatia curenta
        FindCurrentPlaceRequest request = FindCurrentPlaceRequest.newInstance(Arrays.asList(Place.Field.NAME, Place.Field.ADDRESS));

        // Trimite cererea catre Places API și gestioneaza raspunsul
        placesClient.findCurrentPlace(request).addOnSuccessListener((response) -> {
            for (com.google.android.libraries.places.api.model.PlaceLikelihood placeLikelihood : response.getPlaceLikelihoods()) {
                com.google.android.libraries.places.api.model.Place place = placeLikelihood.getPlace();
                Log.i(TAG, "Nearby place: " + placeLikelihood.getLikelihood() + " Name: " + place.getName() + ", Address: " + place.getAddress());
                ConcretPlaces concretPlaces= new ConcretPlaces(place.getName(), place.getAddress());
                placeDao.insert(concretPlaces);
            }
        }).addOnFailureListener((exception) -> {
            if (exception instanceof ApiException) {
                ApiException apiException = (ApiException) exception;
                Log.e(TAG, "Place not found: " + apiException.getStatusCode());
            }
        });



    }

    public void sendMessage(String telefon, String msg) {
        if (!msg.isEmpty() && !telefonMama.isEmpty()) {
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(telefonMama, null, msg, null, null);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Failed to send message", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Some fields are empty", Toast.LENGTH_LONG).show();
        }
    }

    //-----------------------------------------------------------
    //serviciul pentru vreme
    //retrofit este o bilioteca pentru crearea
    //de interogari HTTP si tratarea raspunsurilor din retea
    public interface WeatherService {
        //interfata interogheaza api ul site ului pt datele despre vreme
        @GET("data/2.5/weather")
        //se face o cerere catre serverul openWeather.
        //cerere pentru datele despre vreme
        Call<Weather> getWeather(@Query("q") String city, @Query("appid") String apiKey, @Query("units") String units);
    }

    public void getWeather(Context context, String city) {
        //initializam retrofit -
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // creem o instanta a interfetei
        WeatherService service = retrofit.create(WeatherService.class);

        // face cererea efectiva catre server
        Call<Weather> call = service.getWeather(city, API_KEY, "metric");
        //se foloseste enqueue pentru a trata raspunsurile asincron
        //din retea
        call.enqueue(new retrofit2.Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, retrofit2.Response<Weather> response) {
                if (response.isSuccessful()) {
                    weatherResponse = response.body();
                    if (weatherResponse != null) {
                        double temperatura = weatherResponse.getMain().getTemp();
                        Toast.makeText(context, "The temperature in Bistrita" + String.valueOf(temperatura), Toast.LENGTH_LONG).show();
                    } else {
                        Log.d("Response body is null", "No data received");
                    }
                } else {
                    Log.d("Request unsuccessful", "Error code: " + response.code());
                }
            }


            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                System.out.println("eroare " + t.getMessage());
            }
        });
    }

    public void isClose() {
        Random random = new Random();
        double randomNumber = random.nextDouble();

        if (randomNumber < 0.2) {
            Toast.makeText(getApplicationContext(), "este aproape de tine mama", Toast.LENGTH_LONG).show();
        } else if (randomNumber >= 0.2 && randomNumber < 0.3) {
            Toast.makeText(getApplicationContext(), "este aproape de tine tata", Toast.LENGTH_LONG).show();
        } else if (randomNumber >= 0.3 && randomNumber < 0.5) {
            Toast.makeText(getApplicationContext(), "este aproape de tine sora", Toast.LENGTH_LONG).show();
        } else if (randomNumber == 0.5) {
            Toast.makeText(getApplicationContext(), "nimeni nu e aproape de tine", Toast.LENGTH_LONG).show();
            tractareApel();

        }
    }

    public void tractareApel()
    {
        String phoneNumber = "0743781722";
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Tractare")
                .setMessage("Nu ti face griji. Familia a sunat la tractare.");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}