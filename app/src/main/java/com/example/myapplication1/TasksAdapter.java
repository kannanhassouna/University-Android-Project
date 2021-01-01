package com.example.myapplication1;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.models.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {
    private Context ctx;
    private List<Task> list;
    private String todoListID;
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    TasksAdapter(Context ctx, List<Task> list, String todoListID) {
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        this.ctx = ctx;
        this.list = list;
        this.todoListID = todoListID;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.task_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task todo = list.get(position);
        holder.title.setText(todo.getTitle());
        if(todo.isChecked()) {
            holder.checkedIcon.setImageResource(R.drawable.checkbox_checked);
            holder.title.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }

        holder.delete.setOnClickListener(V -> {
            FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("lists").child(this.todoListID).child("tasks").child(todo.getId()).removeValue();

        });

        holder.checkedIcon.setOnClickListener(V -> {
            if(todo.isChecked()) {
                holder.checkedIcon.setImageResource(R.drawable.checkbox_unchecked);
                holder.title.setPaintFlags(holder.title.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("lists").child(this.todoListID).child("tasks").child(todo.getId()).child("checked").setValue(false);
                todo.setChecked(false);
            } else {
                holder.checkedIcon.setImageResource(R.drawable.checkbox_checked);
                holder.title.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("lists").child(this.todoListID).child("tasks").child(todo.getId()).child("checked").setValue(true);
                todo.setChecked(true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView delete;
        ImageView checkedIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.name);
            delete = itemView.findViewById(R.id.delete);
            checkedIcon = itemView.findViewById(R.id.check_image);

        }
    }
}
