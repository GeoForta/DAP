package com.example.ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.example.ex3.AdaugareSing;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = new Intent(MainActivity.this, detalii.class);

        Button ionut = findViewById(R.id.ionut);
        Button maria = findViewById(R.id.maria);
        Button andrei = findViewById(R.id.andrei);
        Button ana = findViewById(R.id.ana);
        Button andreea = findViewById(R.id.andreea);


        ionut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("nume", "Ionut");
                intent.putExtra("prenume", "Popescu");
                intent.putExtra("telefon", "0787548516");
                intent.putExtra("adresa", "Strada Eminescu nr 5");
                int imageResourceId = getResources().getIdentifier("ionut", "drawable", getPackageName());
                intent.putExtra("imageResourceId", imageResourceId);
                startActivity(intent);
            }
        });


        maria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("nume", "Maria");
                intent.putExtra("prenume", "Loren");
                intent.putExtra("telefon", "0787532417");
                intent.putExtra("adresa", "Strada Lalelelor nr 20");
                int imageResourceId = getResources().getIdentifier("maria", "drawable", getPackageName());
                intent.putExtra("imageResourceId", imageResourceId);
                startActivity(intent);
            }
        });
        andrei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("nume", "Andrei");
                intent.putExtra("prenume", "Rus");
                intent.putExtra("telefon", "0723548517");
                intent.putExtra("adresa", "Strada Piatra Fantanelelor nr 53");
                int imageResourceId = getResources().getIdentifier("andrei", "drawable", getPackageName());
                intent.putExtra("imageResourceId", imageResourceId);
                startActivity(intent);
            }
        });

        ana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("nume", "Ana");
                intent.putExtra("prenume", "Larisa");
                intent.putExtra("telefon", "0723548539");
                intent.putExtra("adresa", "Strada Lacrimioarelor nr 2");
                int imageResourceId = getResources().getIdentifier("ana", "drawable", getPackageName());
                intent.putExtra("imageResourceId", imageResourceId);
                startActivity(intent);
            }
        });

        andreea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("nume", "Andreea");
                intent.putExtra("prenume", "Elena");
                intent.putExtra("telefon", "07235432539");
                intent.putExtra("adresa", "Strada Frunzelor nr 2");
                int imageResourceId = getResources().getIdentifier("andreea", "drawable", getPackageName());
                intent.putExtra("imageResourceId", imageResourceId);
                startActivity(intent);
            }
        });

        Button adauga = findViewById(R.id.add);

        adauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, add.class);
                startActivity(intent);
            }
        });
        AdaugareSing adaugareSing = AdaugareSing.getInstance();
        List<Persoana> persoaneList = adaugareSing.getPersoaneList();

        LinearLayout layout = findViewById(R.id.layout);
        for (Persoana persoana : persoaneList) {
            final String nume = persoana.getNume();
            final String prenume = persoana.getPrenume();
            final String telefon = persoana.getTelefon();
            final String adresa = persoana.getAdresa();

            Button buton = new Button(this);
            buton.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            buton.setText(nume);
            buton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent detaliiIntent = new Intent(MainActivity.this, detalii.class);
                    detaliiIntent.putExtra("nume", nume);
                    detaliiIntent.putExtra("prenume", prenume);
                    detaliiIntent.putExtra("telefon", telefon);
                    detaliiIntent.putExtra("adresa", adresa);
                    startActivity(detaliiIntent);
                }
            });
            layout.addView(buton);
        }

    }
}
