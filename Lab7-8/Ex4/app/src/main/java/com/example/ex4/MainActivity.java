package com.example.ex4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyService myService;

    EditText text;
    EditText text2;
    EditText text3;
    EditText text4;
    Persoane persoana;

    Button edit;

    LinearLayout linear1, linear2, linear3, linear4;
    ScrollView[] scrollViewArray = new ScrollView[4];
    int currentScrollViewIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button add = findViewById(R.id.add);
        Button delete = findViewById(R.id.delete);
        Button filter = findViewById(R.id.filter);
        Button sort = findViewById(R.id.sort);
        edit = findViewById(R.id.edit);

        scrollViewArray[0] = findViewById(R.id.scroll1);
        scrollViewArray[1] = findViewById(R.id.scroll2);
        scrollViewArray[2] = findViewById(R.id.scroll3);
        scrollViewArray[3] = findViewById(R.id.scroll4);

        linear1 = findViewById(R.id.linear1);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = MainActivity.this;

                if (currentScrollViewIndex < scrollViewArray.length) {
                    ScrollView currentScrollView = scrollViewArray[currentScrollViewIndex];

                    LinearLayout layout = new LinearLayout(context);
                    layout.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    layout.setOrientation(LinearLayout.VERTICAL);

                    text = new EditText(context);
                    text2 = new EditText(context);
                    text3 = new EditText(context);
                    text4 = new EditText(context);

                    text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                    text.setTextColor(Color.BLACK);

                    text2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                    text2.setTextColor(Color.BLACK);

                    text3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                    text3.setTextColor(Color.BLACK);

                    text4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                    text4.setTextColor(Color.BLACK);

                    layout.addView(text);
                    layout.addView(text2);
                    layout.addView(text3);
                    layout.addView(text4);

                    currentScrollView.addView(layout);
                    currentScrollViewIndex++;
                } else {
                    Toast.makeText(MainActivity.this, "nu mai sunt scrolluri disponibile", Toast.LENGTH_SHORT).show();
                }
            }
        });



        Button save = findViewById(R.id.salvare);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    public void editeazaPersoana() {
        persoana.setProbleme(10);
        text4.setText(String.valueOf(persoana.getProbleme()));
    }
}
