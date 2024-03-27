package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = findViewById(R.id.textView);

        OkHttpClient client = new OkHttpClient(); //ob pentru a efectua cererea http

        String url = "https://reqres.is/api/users?page=2";

        Request request = new Request.Builder() //ob utilizant URL ul definit
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() { //cererea asincron
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful())
                {
                    final String myResponse = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            text.setText(myResponse);
                        }

                    });
                }
            }
        });
    }
}