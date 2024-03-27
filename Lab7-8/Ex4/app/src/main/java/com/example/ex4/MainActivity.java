package com.example.ex4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyService myService;

    EditText editText, editText2, editText3, editText4;
    EditText edit1, edit2, edit3, edit4;
    int scor1;

    String textIntrodus, textIntrodus2, textIntrodus3, textIntrodus4;
    String textIntrodus11, textIntrodus22, textIntrodus33, textIntrodus44;
    String mesaj1, mesaj2, mesaj3, mesaj4;
    String infov2;
    String info2v2;
    String info3v2;
    String info4v2;
    Persoane persoana;
    Persoane persoana2;
    View v;
    String editare;
    String editare2;
    Button edit;
    String info;
    String info2;
    String info3;
    String info4;

    LinearLayout linear1, linear2, linear3, linear4;
    LinearLayout linear11, linear22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button add = findViewById(R.id.add);
        Button delete = findViewById(R.id.delete);
        Button filter = findViewById(R.id.filter);
        Button sort = findViewById(R.id.sort);
        edit = findViewById(R.id.edit);

        linear1 = findViewById(R.id.firstPairLayout);
        linear2 = findViewById(R.id.secondPairLayout);

        linear11 = findViewById(R.id.Pereche2nr1);
        linear22 = findViewById(R.id.Pereche2nr2);

            Toast.makeText(getApplicationContext(), "Introduceti date ", Toast.LENGTH_SHORT).show();
            Context context = MainActivity.this;

            //text = new EditText(context);
            //text2 = new EditText(context);
            //text3 = new EditText(context);
            // text4 = new EditText(context);

            editText = findViewById(R.id.editText1);
            editText2 = findViewById(R.id.editText2);
            editText3 = findViewById(R.id.editText3);
            editText4 = findViewById(R.id.editText4);

            edit1 = findViewById(R.id.editText11);
            edit2 = findViewById(R.id.editText21);
            edit3 = findViewById(R.id.editText31);
            edit4 = findViewById(R.id.editText41);


            persoana = new Persoane();
            persoana2 = new Persoane();


            myService = new MyService(persoana, persoana2);


        Button save = findViewById(R.id.salvare);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Stergere", Toast.LENGTH_SHORT).show();
                editText.setText("");
                editText2.setText("");
                editText3.setText("");
                editText4.setText("");

                edit1.setText("");
                edit2.setText("");
                edit3.setText("");
                edit4.setText("");
            }
        });

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sorteaza();
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myService.filtScor() == true)
                {
                    edit1.setText("");
                    edit2.setText("");
                    edit3.setText("");
                    edit4.setText("");
                }
                else
                {

                    editText.setText("");
                    editText2.setText("");
                    editText3.setText("");
                    editText4.setText("");
                }
            }
        });


    }



    public void editeazaPersoana() {
        if (editText4 != null && !editText4.getText().toString().isEmpty()) {
            editare = editText4.getText().toString();
            persoana.setProbleme(Integer.parseInt(editare));
        } else {
            Toast.makeText(getApplicationContext(), "introduceti un numar", Toast.LENGTH_SHORT).show();
        }

        if (edit4 != null && !edit4.getText().toString().isEmpty()) {
            editare2 = edit4.getText().toString();
            persoana2.setProbleme(Integer.parseInt(editare2));
        } else {
            Toast.makeText(getApplicationContext(), "introduceti un numar", Toast.LENGTH_SHORT).show();
        }


        Salvare(v);
    }

        public void Sorteaza() {
            if (persoana != null && persoana2 != null) {
                if (persoana.getScor() > persoana2.getScor()) {
                    Toast.makeText(getApplicationContext(), "Persoana 1 are scorul mai mare" , Toast.LENGTH_SHORT).show();
                } else if(persoana.getScor() < persoana2.getScor()){
                    Toast.makeText(getApplicationContext(), "Persoana 2 are scorul mai mare", Toast.LENGTH_SHORT).show();
                    info = edit1.getText().toString();
                    info2 = edit2.getText().toString();
                    info3 = edit3.getText().toString();
                    info4 = edit4.getText().toString();

                    infov2 = editText.getText().toString();
                    info2v2 = editText2.getText().toString();
                    info3v2 = editText3.getText().toString();
                    info4v2 = editText4.getText().toString();

                    editText.setText(info);
                    editText2.setText(info2);
                    editText3.setText(info3);
                    editText4.setText(info4);

                    edit1.setText(infov2);
                    edit2.setText(info2v2);
                    edit3.setText(info3v2);
                    edit4.setText(info4v2);


                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Sunt egale", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Persoanele nu au fost completate corect", Toast.LENGTH_SHORT).show();
            }
        }


        public void Salvare(View v) {


            textIntrodus11 = edit1.getText().toString();
            if (!textIntrodus11.isEmpty()) {
                persoana2.setNume(textIntrodus11);
            }
            if (editText2 != null) {
                textIntrodus2 = editText2.getText().toString();
                if (!textIntrodus2.isEmpty()) {
                    persoana.setPrenume(textIntrodus2);
                }
            }
            if (edit2 != null) {
                textIntrodus22 = edit2.getText().toString();
                if (!textIntrodus22.isEmpty()) {
                    persoana2.setPrenume(textIntrodus22);
                }
            }
            if (editText3 != null) {
                textIntrodus3 = editText3.getText().toString();
                if (!textIntrodus3.isEmpty()) {
                    persoana.setScor(Integer.parseInt(textIntrodus3));
                }
            }
            if (edit3 != null) {
                textIntrodus33 = edit3.getText().toString();
                if (!textIntrodus33.isEmpty()) {
                    persoana2.setScor(Integer.parseInt(textIntrodus33));
                }
            }


            if (editText4 != null) {
                textIntrodus4 = editText4.getText().toString();
                if (!textIntrodus4.isEmpty()) {
                    persoana.setProbleme(Integer.parseInt(textIntrodus4));
                }

            }
            if (edit4 != null) {
                textIntrodus44 = edit4.getText().toString();
                if (!textIntrodus44.isEmpty()) {
                    persoana2.setProbleme(Integer.parseInt(textIntrodus44));
                }
            }

            Log.i("Verifcare" , "persoana1" + textIntrodus4);
            Log.i("Verifcare" , "persoana2" + textIntrodus44);


            if (myService.verificareProbleme() == false) {
                Toast.makeText(getApplicationContext(), "Nu sunt suficiente probleme, editeaza", Toast.LENGTH_SHORT).show();
            }
            else
                if (myService.verificareProbleme() == true)
                {
                    Toast.makeText(getApplicationContext(), "sunt suficiente probleme", Toast.LENGTH_SHORT).show();
                }
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editeazaPersoana();
                }
            });

            if (myService.verificareProbleme2() == false && !edit4.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Nu sunt suficiente probleme, editeaza al doilea camp", Toast.LENGTH_SHORT).show();
            }
            else
            if (myService.verificareProbleme2() == true)
            {
                Toast.makeText(getApplicationContext(), "sunt suficiente probleme pentru al doilea", Toast.LENGTH_SHORT).show();
            }
        }
}
