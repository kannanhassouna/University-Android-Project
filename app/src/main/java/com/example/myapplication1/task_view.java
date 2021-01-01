package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication1.models.Task;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;


public class task_view extends AppCompatActivity implements View.OnClickListener{
    FirebaseAuth mAuth;
    FirebaseUser user;
    String listID;
    String taskID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        findViewById(R.id.back).setOnClickListener(this);
        TextView title = findViewById(R.id.title);
        findViewById(R.id.delete).setOnClickListener(this);
        TextView description = findViewById(R.id.description);

        listID = getIntent().getStringExtra("LIST_ID");
        taskID = getIntent().getStringExtra("TASK_ID");

        FirebaseDatabase
                .getInstance()
                .getReference("Users")
                .child(user.getUid())
                .child("lists")
                .child(listID)
                .child("tasks")
                .child(taskID)
                .addValueEventListener(new ValueEventListener(){

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Task task = snapshot.getValue(Task.class);
                        title.setText(task.getTitle());
                        Toast.makeText(task_view.this, task.getTitle() + " 123 ", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                } );

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back: {onBackPressed(); break;}
            case R.id.delete: {
                FirebaseDatabase
                        .getInstance()
                        .getReference("Users")
                        .child(user.getUid())
                        .child("lists")
                        .child(listID)
                        .child("tasks")
                        .child(taskID)
                        .removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                onBackPressed();
                            }
                        });break;
            }
            default : {}
        }
    }
}