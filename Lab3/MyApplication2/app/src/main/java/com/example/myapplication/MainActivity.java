package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button apasare;
    Button contor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apasare = (Button) findViewById(R.id.button2);

        contor = (Button) findViewById(R.id.button3);

        TextView text = findViewById(R.id.textView);




        apasare.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getApplicationContext(), "Butonul a fost apasat", Toast.LENGTH_LONG).show();            }
        });
        contor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                int valoare = Integer.parseInt(text.getText().toString());
                valoare++;

                text.setText(String.valueOf(valoare));
            }
        });

    }

}

