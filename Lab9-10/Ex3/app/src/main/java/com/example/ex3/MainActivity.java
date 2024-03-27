package com.example.ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.example.ex3.SendMessage;


public class MainActivity extends AppCompatActivity {

    EditText edit;
    private myServer server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = (EditText) findViewById(R.id.editText);

        server = new myServer();
        server.start();
    }

    public void Send(View v)
    {
        SendMessage messageSender = new SendMessage();
        messageSender.execute(edit.getText().toString());
    }
}