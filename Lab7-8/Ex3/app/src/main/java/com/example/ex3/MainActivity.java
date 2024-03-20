package com.example.ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyService Myservice;
    private char[][] tabla = new char[3][3];
    private boolean isBound = false;
    EditText text1, text2, text3, text4, text5, text6, text7, text8, text9;

    private ServiceConnection connection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            MyService.LocalBinder binder = (MyService.LocalBinder) service;
            Myservice = binder.getService();
            isBound = true; //am conectat serviciul
        }
        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            isBound = false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, MyService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);

        text1 = findViewById(R.id.editTextText);
        text1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String info1 = text1.getText().toString();
                    Myservice.placeSymbol(0, 0, info1.charAt(0));
                    return true;
                }
                return false;
            }
        });

        text2 = findViewById(R.id.editTextText2);
        text2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String info2 = text2.getText().toString();
                    Myservice.placeSymbol(0, 1, info2.charAt(0));
                    return true;
                }
                return false;
            }
        });
        text3 = findViewById(R.id.editTextText3);
        text3.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String info3 = text3.getText().toString();
                    Myservice.placeSymbol(0, 2, info3.charAt(0));
                    return true;
                }
                return false;
            }
        });


        text4 = findViewById(R.id.editTextText4);
        text4.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String info4 = text4.getText().toString();
                    Myservice.placeSymbol(1, 0, info4.charAt(0));
                    return true;
                }
                return false;
            }
        });

        text5 = findViewById(R.id.editTextText5);
        text5.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String info5 = text5.getText().toString();
                    Myservice.placeSymbol(1, 1, info5.charAt(0));
                    return true;
                }
                return false;
            }
        });

        text6 = findViewById(R.id.editTextText6);
        text6.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String info6 = text6.getText().toString();
                    Myservice.placeSymbol(1, 2, info6.charAt(0));
                    return true;
                }
                return false;
            }
        });

        text7 = findViewById(R.id.editTextText7);
        text7.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String info7 = text7.getText().toString();
                    Myservice.placeSymbol(2, 0, info7.charAt(0));
                    return true;
                }
                return false;
            }
        });

        text8 = findViewById(R.id.editTextText8);
        text8.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String info8 = text8.getText().toString();
                    Myservice.placeSymbol(2, 1, info8.charAt(0));
                    return true;
                }
                return false;
            }
        });

        text9 = findViewById(R.id.editTextText9);
        text9.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String info9 = text9.getText().toString();
                    Myservice.placeSymbol(2, 2, info9.charAt(0));
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBound) {
            unbindService(connection);
            isBound = false;
        }
    }
}