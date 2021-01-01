package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        TextView signUp = findViewById(R.id.sign_up);
        TextView forgetPassword = findViewById(R.id.forget_password);
        TextView login = findViewById(R.id.login);


        signUp.setOnClickListener(V -> {
            startActivity(new Intent(this, sign_up.class));
        });

        forgetPassword.setOnClickListener(V -> {
            startActivity(new Intent(this, forget_password.class));
        });

        login.setOnClickListener(V -> {
            String email = ((EditText) findViewById(R.id.email)).getText().toString();
            String password = ((EditText) findViewById(R.id.password)).getText().toString();
            login(email, password);
        });

    }

    private void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(getApplicationContext(), ListsActivity.class));
                        } else {
                            Log.e("LOGIN", "error");
                            Toast.makeText(login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}