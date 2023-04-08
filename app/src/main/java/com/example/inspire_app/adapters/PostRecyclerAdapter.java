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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.compose.ui.graphics.Color;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inspire_app.R;
import com.example.inspire_app.interfaces.Commentonclickrecycler;
import com.example.inspire_app.interfaces.LikedOnclickrecycler;
import com.example.inspire_app.interfaces.Postonclickrecyclerview;
import com.example.inspire_app.models.PostData;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.MyViewHolder>{
    Context context;
    List<PostData> dataList;
    int color;
    Boolean check = false;
    Postonclickrecyclerview postonclickrecyclerview;
    LikedOnclickrecycler likedOnclickrecycler;
    Commentonclickrecycler commentonclickrecycler;


    public PostRecyclerAdapter(Context context,List<PostData> dataList,Postonclickrecyclerview postonclickrecyclerview,LikedOnclickrecycler likedOnclickrecycler,Commentonclickrecycler commentonclickrecycler) {
        this.dataList = dataList;
        this.context = context;
        this.postonclickrecyclerview = postonclickrecyclerview;
        this.likedOnclickrecycler = likedOnclickrecycler;
        this.commentonclickrecycler = commentonclickrecycler;
    }
    @NonNull
    @Override
    public PostRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_item,parent,false);
        return new PostRecyclerAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PostRecyclerAdapter.MyViewHolder holder, int position) {
        if(dataList.get(position).getComments() == true){
            holder.commentsection.setVisibility(View.VISIBLE);
        }
        else{
            holder.commentsection.setVisibility(View.GONE);
            holder.textView.setText("No comments");
        }
        holder.category.setText("# " + dataList.get(position).getCategory());
        holder.content.setText(dataList.get(position).getContent());
        holder.org.setText(dataList.get(position).getOrganization());
        Picasso.with(context).load("http://192.168.1.105:3000" + dataList.get(position).getImageurl()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postonclickrecyclerview.onclick(dataList.get(position).get_id());
            }
        });
        holder.liked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                likedOnclickrecycler.onclick(dataList.get(position).get_id(),dataList.get(position).getCategory(),dataList.get(position).getOrganization(),dataList.get(position).getImageurl());
                v.setClickable(false);
            }
        });
        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentonclickrecycler.onclick(holder.textInputEditText.getText().toString(),dataList.get(position).get_id());
                holder.textInputEditText.setText(" ");
            }
        });






    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView category,content,org;
        ImageView liked;
        TextView textView;
        LinearLayout commentsection;
        TextInputEditText textInputEditText;
        MaterialButton submit;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.post_image);
            textInputEditText = itemView.findViewById(R.id.comment_input);
            category = itemView.findViewById(R.id.category);
            content = itemView.findViewById(R.id.post_content);
            org = itemView.findViewById(R.id.org_content);
            liked = itemView.findViewById(R.id.likedbtn);
            textView = itemView.findViewById(R.id.comment_text);
            commentsection = itemView.findViewById(R.id.comment_section);
            submit = itemView.findViewById(R.id.submitbtn);


        }
    }
}
