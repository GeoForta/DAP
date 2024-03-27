package com.example.ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SendMessage extends AsyncTask<String, Void, Void> {

    Socket s;
    DataOutputStream dos;
    PrintWriter pw;
    @Override
    protected Void doInBackground(String... voids)
    {
        String message = voids[0];

        try
        {
            s = new Socket("192.168.100.21", 7800);
            pw = new PrintWriter(s.getOutputStream());
            pw.write(message);
            pw.flush();
            pw.close();
            s.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }

        return null;
        }
}