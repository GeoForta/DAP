package com.example.ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Button save = findViewById(R.id.save);

        Intent intent = getIntent();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText numeadauga = findViewById(R.id.numeadauga);
                EditText prenumeadauga = findViewById(R.id.prenumeadauga);
                EditText adresaadauga = findViewById(R.id.adresaadauga);
                EditText telefonadauga = findViewById(R.id.telefonadauga);

                String nume = numeadauga.getText().toString();
                String prenume = prenumeadauga.getText().toString();
                String adresa = adresaadauga.getText().toString();
                String telefon = telefonadauga.getText().toString();

                Persoana persoana = new Persoana(nume, prenume, telefon, adresa);

                AdaugareSing.getInstance().adaugaPersoana(persoana);

                finish();
            }
        });
    }
}