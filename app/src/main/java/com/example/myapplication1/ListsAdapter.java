package com.example.myapplication1;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.models.TasksList;

import java.util.List;

public class ListsAdapter extends RecyclerView.Adapter<ListsAdapter.ViewHolder> {
    Context ctx;
    List<TasksList> list;

    ListsAdapter(Context ctx, List<TasksList> list) {

        this.ctx = ctx;
        this.list = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView tasksCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            tasksCount = itemView.findViewById(R.id.tasks);
            itemView.setOnClickListener(V -> {
                Log.d("POP", "Enter");
                Intent intent = new Intent(ctx, TasksActivity.class);
                intent.putExtra("TODO_LIST_ID", list.get(getAdapterPosition()).getId());
                intent.putExtra("TODO_LIST_TITLE", list.get(getAdapterPosition()).getTitle());
                ctx.startActivity(intent);
            });
        }
    }

    @NonNull
    @Override
    public ListsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layout = LayoutInflater.from(ctx);
        View view = layout.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListsAdapter.ViewHolder holder, int position) {
        TasksList todo = (TasksList) list.get(position);
        holder.title.setText(todo.getTitle());
        holder.tasksCount.setText(todo.getTasks().size() + " Tasks");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
