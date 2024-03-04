package com.example.ex2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragmentManager = getSupportFragmentManager();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, Fragment1.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView2, fragment2.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit();

    }
    public void navigateToFragment1() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, new Fragment1())
                .addToBackStack(null)
                .commit();
    }
}
