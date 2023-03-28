package com.example.inspire_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inspire_app.R;
import com.example.inspire_app.interfaces.Horzonclickrecycler;
import com.example.inspire_app.models.PostData;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class HorzRecyclerAdapter extends RecyclerView.Adapter<HorzRecyclerAdapter.MyViewHolder>{
    Context context;
    List<String> data;
    Horzonclickrecycler horzonclickrecycler;



    public HorzRecyclerAdapter(Context context,List<String> data,Horzonclickrecycler horzonclickrecycler) {
        this.context = context;
        this.data = data;
        this.horzonclickrecycler = horzonclickrecycler;
    }
    @NonNull
    @Override
    public HorzRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.horz_scroll_item,parent,false);
        return new HorzRecyclerAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HorzRecyclerAdapter.MyViewHolder holder, int position) {
        holder.horzbtn.setText(data.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                horzonclickrecycler.onclick(data.get(position));
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        MaterialButton horzbtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            horzbtn = itemView.findViewById(R.id.horz_text);


        }
    }
}
