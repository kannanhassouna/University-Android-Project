package com.example.myapplication1.models;

import java.util.HashMap;

public class TodoTasks {

    private String id;
    private String title;
    private HashMap<String, TodoTask> tasks = new HashMap<>();

    public TodoTasks() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HashMap<String, TodoTask> getTasks() {
        return tasks;
    }

    public void setTasks(HashMap<String, TodoTask> tasks) {
        this.tasks = tasks;
    }
}

