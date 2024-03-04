package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button suma = findViewById(R.id.button);
        Button scadere = findViewById(R.id.button2);
        Button impartire = findViewById(R.id.button3);
        Button inmultire = findViewById(R.id.button4);

        EditText numar1text = findViewById(R.id.numar1text);
        EditText numar2text = findViewById(R.id.numar2text);
        TextView textView4 = findViewById(R.id.textView4);

        suma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numar1;
                String numar2;
                numar1 = numar1text.getText().toString();
                numar2 = numar2text.getText().toString();

                int nr1 = Integer.parseInt(numar1);
                int nr2 = Integer.parseInt(numar2);

                int suma = nr1 + nr2;
                textView4.setText(String.valueOf(suma));
            }


        });

        scadere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String numar1;
                String numar2;

                numar1 = numar1text.getText().toString();
                numar2 = numar2text.getText().toString();

                int nr1 = Integer.parseInt(numar1);
                int nr2 = Integer.parseInt(numar2);

                int scadere;

                scadere = nr1 - nr2;
                textView4.setText(String.valueOf(scadere));
            }

            });

        impartire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String numar1;
                String numar2;

                numar1 = numar1text.getText().toString();
                numar2 = numar2text.getText().toString();

                int nr1 = Integer.parseInt(numar1);
                int nr2 = Integer.parseInt(numar2);

                int impartire;

                impartire = nr1 / nr2;
                textView4.setText(String.valueOf(impartire));
            }

        });

        inmultire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String numar1;
                String numar2;

                numar1 = numar1text.getText().toString();
                numar2 = numar2text.getText().toString();

                int nr1 = Integer.parseInt(numar1);
                int nr2 = Integer.parseInt(numar2);

                int inmultire;

                inmultire = nr1 * nr2;
                textView4.setText(String.valueOf(inmultire));
            }

        });
    }
}
