package com.example.inspire_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inspire_app.R;
import com.example.inspire_app.interfaces.Postonclickrecyclerview;
import com.example.inspire_app.interfaces.RemoveOnclickrecycler;
import com.example.inspire_app.models.GetLiked;
import com.example.inspire_app.models.PostData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LikedPostRecyclerAdaptere extends RecyclerView.Adapter<LikedPostRecyclerAdaptere.MyViewHolder>{
    Context context;
    Postonclickrecyclerview postonclickrecyclerview;
    List<GetLiked> data;
    RemoveOnclickrecycler removeOnclickrecycler;


    public LikedPostRecyclerAdaptere(Context context,List<GetLiked> data,Postonclickrecyclerview postonclickrecyclerview,RemoveOnclickrecycler removeOnclickrecycler) {
        this.context = context;
        this.data = data;
        this.postonclickrecyclerview = postonclickrecyclerview;
        this.removeOnclickrecycler = removeOnclickrecycler;
    }
    @NonNull
    @Override
    public LikedPostRecyclerAdaptere.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.liked_post_item,parent,false);
        return new LikedPostRecyclerAdaptere.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull LikedPostRecyclerAdaptere.MyViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postonclickrecyclerview.onclick(data.get(position).getPostid().get_id());
            }
        });
        Picasso.with(context).load("http://192.168.1.103:3000" + data.get(position).getPostid().getImageurl()).into(holder.imageView);
        holder.category.setText(data.get(position).getPostid().getOrganization() + " #"+data.get(position).getPostid().getCategory());
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeOnclickrecycler.onclick(data.get(position).get_id());
                v.setClickable(false);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView category;
        ImageView remove;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.liked_image);
            category = itemView.findViewById(R.id.post_title);
            remove = itemView.findViewById(R.id.remove_btn);



        }
    }
}
