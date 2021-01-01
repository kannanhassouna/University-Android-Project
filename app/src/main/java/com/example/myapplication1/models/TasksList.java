package com.example.myapplication1.models;

import java.util.HashMap;

public class TasksList {

    private String id;
    private String title;
    private HashMap<String, Task> tasks = new HashMap<>();

    public TasksList() {

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

    public HashMap<String, Task> getTasks() {
        return tasks;
    }

    public void setTasks(HashMap<String, Task> tasks) {
        this.tasks = tasks;
    }
}

