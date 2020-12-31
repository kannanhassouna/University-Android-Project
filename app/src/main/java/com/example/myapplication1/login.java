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
        TextView forgetPassword = findViewById(R.id.forget_password);


        signUp.setOnClickListener(V -> {
            startActivity(new Intent(this, sign_up.class));
        });

        forgetPassword.setOnClickListener(V -> {
            startActivity(new Intent(this, forget_password.class));
        });

    }
}