package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView signUp = findViewById(R.id.sign_up);

        signUp.setOnClickListener(V -> {
            startActivity(new Intent(this, sign_up.class));
        });

    }
}