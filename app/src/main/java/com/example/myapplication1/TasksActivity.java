package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication1.models.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TasksActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseUser user;
    FirebaseAuth mAuth;
    TextView createNewTodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        createNewTodo = findViewById(R.id.create_task);
        createNewTodo.setOnClickListener(v -> showNewTaskDialog());

        findViewById(R.id.back).setOnClickListener(V -> onBackPressed());
        recyclerView = findViewById(R.id.tasks_recycler_view);
        View toolbar = findViewById(R.id.toolbar);
        TextView listTitle = findViewById(R.id.todo_list_title);
        listTitle.setText(getIntent().getStringExtra("TODO_LIST_TITLE"));
        TextView pageTitle = toolbar.findViewById(R.id.page_title);
        pageTitle.setText("Things ToDo");
        List<Task> list = new ArrayList<>();


        TasksAdapter tasksAdapter = new TasksAdapter(this, list, getIntent().getStringExtra("TODO_LIST_ID"));
        recyclerView.setAdapter(tasksAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //  Firebase Sync
        FirebaseDatabase
                .getInstance()
                .getReference("Users")
                .child(user.getUid())
                .child("lists")
                .child(getIntent().getStringExtra("TODO_LIST_ID"))
                .child("tasks")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for(DataSnapshot dataSnapshot: snapshot.getChildren()) {
                            list.add(dataSnapshot.getValue(Task.class));
                        }
                        tasksAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void showNewTaskDialog() {
        final EditText listEditText = new EditText(this);
        listEditText.setHint("Enter new task");
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Create new task")
                .setView(listEditText)
                .setPositiveButton("Save", null)
                .setNegativeButton("cancel", null)
                .create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            if (!listEditText.getText().toString().trim().equals("")) {
                dialog.dismiss();
                pushTaskToFirebase(listEditText.getText().toString());
                Toast.makeText(this, "task was created", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void pushTaskToFirebase(String name) {
        Task task = new Task();
        task.setTitle(name);
        String listId = getIntent().getStringExtra("TODO_LIST_ID");
        String key = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("lists").child(listId).child("tasks").push().getKey();
        task.setId(key);
        FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("lists").child(listId).child("tasks").child(key).setValue(task);
    }
}