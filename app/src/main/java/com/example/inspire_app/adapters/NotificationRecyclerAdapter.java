package com.example.inspire_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inspire_app.R;

public class NotificationRecyclerAdapter extends RecyclerView.Adapter<NotificationRecyclerAdapter.MyViewHolder>{
    Context context;


    public NotificationRecyclerAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public NotificationRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notification_item,parent,false);
        return new NotificationRecyclerAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NotificationRecyclerAdapter.MyViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 4;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
