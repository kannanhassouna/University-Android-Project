package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication1.models.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TasksActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        findViewById(R.id.back).setOnClickListener(V -> onBackPressed());
        recyclerView = findViewById(R.id.tasks_recycler_view);
        View toolbar = findViewById(R.id.toolbar);
        TextView listTitle = findViewById(R.id.todo_list_title);
        listTitle.setText(getIntent().getStringExtra("TODO_LIST_TITLE"));
        TextView pageTitle = toolbar.findViewById(R.id.page_title);
        pageTitle.setText("Things ToDo");

        List<Task> list = new ArrayList<>();
        Task task = new Task();
        task.setChecked(true);
        task.setTitle("Task 11");
        list.add(task);
        Task task2 = new Task();
        task2.setChecked(false);
        task2.setTitle("Task 12");
        list.add(task2);

        TasksAdapter tasksAdapter = new TasksAdapter(this, list, "List");
        recyclerView.setAdapter(tasksAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}