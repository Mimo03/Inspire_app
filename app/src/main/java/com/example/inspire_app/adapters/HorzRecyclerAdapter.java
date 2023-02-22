package com.example.inspire_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inspire_app.R;

public class HorzRecyclerAdapter extends RecyclerView.Adapter<HorzRecyclerAdapter.MyViewHolder>{
    Context context;


    public HorzRecyclerAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public HorzRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.horz_scroll_item,parent,false);
        return new HorzRecyclerAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HorzRecyclerAdapter.MyViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 5;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
