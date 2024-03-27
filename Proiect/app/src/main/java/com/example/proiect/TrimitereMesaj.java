package com.example.proiect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.Manifest;



public class TrimitereMesaj extends AppCompatActivity {

    EditText editText, editText2;
    private static final int PERMISSION_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trimitere_mesaj);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);

        //Contacte contacte = new Contacte("Maria", "Popescu", "0787548516");

        //veificam daca avem permisiunea
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    PERMISSION_REQUEST_CODE);
        } else {
            // Permisiunea este deja acordată, puteți continua cu acțiunile dvs.
            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }


        Button trimitere = findViewById(R.id.trimitere);

        trimitere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = editText.getText().toString();
                String msg = editText2.getText().toString();

                if (!msg.isEmpty() && !number.isEmpty()) {
                    try {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(number, null, msg, null, null);
                        Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Failed to send message", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Some fields are empty", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    //gestionam rezultatul solicitarii
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Verificați codul de solicitare și rezultatele acordării permisiunii
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permisiunea a fost acordată, puteți continua cu acțiunile dvs.
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                // Permisiunea a fost respinsă, puteți informa utilizatorul și gestionați în consecință
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
