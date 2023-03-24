package com.example.inspire_app.adapters;

import static com.example.inspire_app.utils.App.getContext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.compose.ui.graphics.Color;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inspire_app.R;
import com.example.inspire_app.interfaces.LikedOnclickrecycler;
import com.example.inspire_app.interfaces.Postonclickrecyclerview;
import com.example.inspire_app.models.PostData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.MyViewHolder>{
    Context context;
    List<PostData> dataList;
    int color;
    Postonclickrecyclerview postonclickrecyclerview;
    LikedOnclickrecycler likedOnclickrecycler;


    public PostRecyclerAdapter(Context context,List<PostData> dataList,Postonclickrecyclerview postonclickrecyclerview,LikedOnclickrecycler likedOnclickrecycler) {
        this.dataList = dataList;
        this.context = context;
        this.postonclickrecyclerview = postonclickrecyclerview;
        this.likedOnclickrecycler = likedOnclickrecycler;
    }
    @NonNull
    @Override
    public PostRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_item,parent,false);
        return new PostRecyclerAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PostRecyclerAdapter.MyViewHolder holder, int position) {
        holder.category.setText("# " + dataList.get(position).getCategory());
        holder.content.setText(dataList.get(position).getContent());
        Picasso.with(context).load(dataList.get(position).getImageurl()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                postonclickrecyclerview.onclick();
            }
        });
        holder.liked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                likedOnclickrecycler.onclick(dataList.get(position).get_id(),dataList.get(position).getCategory(),dataList.get(position).getOrganization(),dataList.get(position).getImageurl());
                v.setClickable(false);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView category,content;
        ImageView liked;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.post_image);
            category = itemView.findViewById(R.id.category);
            content = itemView.findViewById(R.id.post_content);
            liked = itemView.findViewById(R.id.likedbtn);


        }
    }
}
