package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView next =  findViewById(R.id.nextBtn);


        next.setOnClickListener( v -> {
            Intent moveToLogin = new Intent(getApplicationContext(), login.class);
            startActivity(moveToLogin);
        });
    }
}