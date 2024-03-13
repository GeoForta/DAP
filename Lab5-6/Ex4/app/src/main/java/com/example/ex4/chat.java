package com.example.ex4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class chat extends AppCompatActivity {

    ListView chat;
    ArrayAdapter<String> adapter;
    List<String> messages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chat = findViewById(R.id.lista);
        messages = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messages);

        chat.setAdapter(adapter);

        EditText mesaj = findViewById(R.id.editTextText);
        Button buton = findViewById(R.id.button); //trimite
        Button back = findViewById(R.id.back);


        Intent intent = getIntent();
        ListView mesaje = findViewById(R.id.lista);

        String mesajPrimit = getIntent().getStringExtra("message");


        messages.add(mesajPrimit);


        adapter.notifyDataSetChanged();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chat.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mesaj.getText().toString();
                Intent intent = new Intent(chat.this, MainActivity.class);
                intent.putExtra("message", message);
                startActivity(intent);
            }
        });

    }
}