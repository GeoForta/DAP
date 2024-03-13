package com.example.ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class detalii extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalii);

        Intent intent = getIntent();

        String nume = intent.getStringExtra("nume");
        String prenume = intent.getStringExtra("prenume");
        String telefon = intent.getStringExtra("telefon");
        String adresa = intent.getStringExtra("adresa");

        TextView numerasp = findViewById(R.id.numerasp);
        TextView prenumerasp = findViewById(R.id.prenumerasp);
        TextView telefonrasp = findViewById(R.id.telefonrasp);
        TextView adresarasp = findViewById(R.id.adresarasp);

        numerasp.setText(nume);
        prenumerasp.setText(prenume);
        telefonrasp.setText(telefon);
        adresarasp.setText(adresa);

        if (intent != null)
        {
            int imageResourceId = getIntent().getIntExtra("imageResourceId", -1);
            ImageView image = findViewById(R.id.imageView);

            image.setImageResource(imageResourceId);
        }

        Button btn = findViewById(R.id.back);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(detalii.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button call = findViewById(R.id.call);

        call.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + telefon));
                startActivity(intent);
            }
        });
    }
}