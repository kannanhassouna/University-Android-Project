package com.example.myapplication1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication1.models.Task;
import com.example.myapplication1.models.TasksList;

import java.util.ArrayList;
import java.util.HashMap;

public class ListsActivity extends AppCompatActivity {

    private ListsAdapter listsAdapter;
    private RecyclerView listRecyclerView;
    private java.util.List todoLists = new ArrayList<>();
    private java.util.List searchedToDoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
        findViewById(R.id.back).setOnClickListener(V -> onBackPressed());
        findViewById(R.id.create_list).setOnClickListener(V -> showNewListDialog());
        listRecyclerView = findViewById(R.id.lists_recycler_view);
        TasksList todoTasksList = new TasksList();
        todoTasksList.setTitle("ahmed");
        Task todo = new Task();
        todo.setTitle("Ahmed");
        HashMap<String, Task> td_list = new HashMap<>();
        td_list.put("1", todo);

        todoTasksList.setTasks(td_list);
        todoLists.add(todoTasksList);
        listsAdapter = new ListsAdapter(this, todoLists);
        listRecyclerView.setAdapter(listsAdapter);
        listRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
            }

        });
    }


}