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
import com.example.inspire_app.activites.PostActivity;
import com.example.inspire_app.models.PostData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NotificationRecyclerAdapter extends RecyclerView.Adapter<NotificationRecyclerAdapter.MyViewHolder>{
    Context context;
    List<PostData> data = new ArrayList<>();


    public NotificationRecyclerAdapter(Context context,List<PostData> data) {
        this.context = context;
        this.data = data;
    }
    @NonNull
    @Override
    public NotificationRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notification_item,parent,false);
        return new NotificationRecyclerAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NotificationRecyclerAdapter.MyViewHolder holder, int position) {
        holder.content.setText(data.get(position).getOrganization());
        holder.text.setText("New Post on "+data.get(position).getCategory()+" added");
        Picasso.with(context.getApplicationContext()).load("http://192.168.12.127:3000" + data.get(0).getImageurl()).into(holder.image);



    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text,content;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.noti_image);
            text = itemView.findViewById(R.id.noti_text);
            content = itemView.findViewById(R.id.noti_content);


        }
    }
}
