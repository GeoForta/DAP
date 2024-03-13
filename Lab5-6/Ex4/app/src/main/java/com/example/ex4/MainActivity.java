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

public class MainActivity extends AppCompatActivity {

    ListView chat;
    ArrayAdapter<String> adapter;
    List<String> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buton = findViewById(R.id.button2);
        EditText mesaj = findViewById(R.id.editTextText2);

        chat = findViewById(R.id.list);
        messages = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messages);
        chat.setAdapter(adapter);

        Intent intent = getIntent();
        String mesajPrimit = intent.getStringExtra("message");
        if (mesajPrimit != null) {
            messages.add(mesajPrimit);
            adapter.notifyDataSetChanged();
        }

        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mesaj.getText().toString();
                Intent intent = new Intent(MainActivity.this, chat.class);
                intent.putExtra("message", message);
                startActivity(intent);
            }
        });
    }
}