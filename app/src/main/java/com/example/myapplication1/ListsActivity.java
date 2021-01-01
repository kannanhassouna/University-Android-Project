package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication1.models.Task;
import com.example.myapplication1.models.TasksList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListsActivity extends AppCompatActivity {

    private ListsAdapter listsAdapter;
    private RecyclerView listRecyclerView;
    private java.util.List todoLists = new ArrayList<>();
    private java.util.List searchedToDoList = new ArrayList<>();
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        findViewById(R.id.back).setOnClickListener(V -> onBackPressed());
        findViewById(R.id.create_list).setOnClickListener(V -> showNewListDialog());
        findViewById(R.id.logout).setVisibility(View.VISIBLE);
        findViewById(R.id.logout).setOnClickListener(V -> logout());
        listRecyclerView = findViewById(R.id.lists_recycler_view);

        listsAdapter = new ListsAdapter(this, todoLists);
        listRecyclerView.setAdapter(listsAdapter);
        listRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //      Firebase  Snapshot

        if(user != null)
        FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("lists").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                todoLists.clear();
                for(DataSnapshot snapShot: dataSnapshot.getChildren()) {
                    TasksList tasksList = snapShot.getValue(TasksList.class);
                    tasksList.setId(snapShot.getKey());
                    todoLists.add(tasksList);
                }
                listsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showNewListDialog() {
        final EditText listEditText = new EditText(this);
        listEditText.setHint("List name");
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Create new list")
                .setView(listEditText)
                .setPositiveButton("Save", null)
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            if (!listEditText.getText().toString().trim().equals("")) {
                dialog.dismiss();
                String uid = mAuth.getCurrentUser().getUid();
                HashMap<String, Object> data = new HashMap<>();
                HashMap<String, Task> list = new HashMap<>();
                data.put("tasks", list);
                data.put("title", listEditText.getText().toString());
                FirebaseDatabase.getInstance().getReference("Users").child(uid).child("lists").push().setValue(data);
                Toast.makeText(this, "Added Successfully", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void logout() {
        mAuth.getInstance().signOut();
        Toast.makeText(this, "Signed out", Toast.LENGTH_LONG);
        startActivity(new Intent(this, login.class));
    }
}