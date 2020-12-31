package com.example.myapplication1;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.models.TodoTasks;

import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {
    Context ctx;
    List<TodoTasks> list;

    TodoListAdapter(Context ctx, List<TodoTasks> list) {

        this.ctx = ctx;
        this.list = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView tasksCount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            tasksCount = itemView.findViewById(R.id.tasks);
        }
    }

    @NonNull
    @Override
    public TodoListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layout =  LayoutInflater.from(ctx);
        View view = layout.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListAdapter.ViewHolder holder, int position) {
        TodoTasks todo = list.get(position);
        holder.title.setText(todo.getTitle());
        holder.tasksCount.setText(todo.getTasks().size() + " Tasks");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
