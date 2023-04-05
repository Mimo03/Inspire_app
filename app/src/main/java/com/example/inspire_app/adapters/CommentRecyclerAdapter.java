package com.example.inspire_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inspire_app.R;
import com.example.inspire_app.interfaces.Postonclickrecyclerview;
import com.example.inspire_app.interfaces.RemoveOnclickrecycler;
import com.example.inspire_app.models.GetLiked;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CommentRecyclerAdapter extends RecyclerView.Adapter<CommentRecyclerAdapter.MyViewHolder>{
    Context context;
    List<String> data;


    public CommentRecyclerAdapter(Context context,List<String> data) {
        this.context = context;
        this.data = data;
    }
    @NonNull
    @Override
    public CommentRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_item,parent,false);
        return new CommentRecyclerAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CommentRecyclerAdapter.MyViewHolder holder, int position) {
        holder.comment.setText(data.get(position));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView comment;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            comment = itemView.findViewById(R.id.comment);



        }
    }
}
