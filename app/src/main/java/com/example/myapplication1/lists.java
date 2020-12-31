package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication1.models.TodoTask;
import com.example.myapplication1.models.TodoTasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class lists extends AppCompatActivity {

    private TodoListAdapter todoListAdapter;
    private RecyclerView listRecyclerView;
    private List<TodoTasks> todoLists = new ArrayList<>();
    private List<TodoTasks> searchedToDoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
        listRecyclerView = findViewById(R.id.lists_recycler_view);
        TodoTasks todoList = new TodoTasks();
        todoList.setTitle("ahmed");
        TodoTask todo = new TodoTask();
        todo.setTitle("Ahmed");
        HashMap<String, TodoTask> td_list = new HashMap<>();
        td_list.put("1", todo);

        todoList.setTasks(td_list);
        todoLists.add(todoList);
        todoListAdapter = new TodoListAdapter(this, todoLists);
        listRecyclerView.setAdapter(todoListAdapter);
        listRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}