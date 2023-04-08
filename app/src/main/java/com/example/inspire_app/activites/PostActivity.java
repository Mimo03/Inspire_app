package com.example.inspire_app.activites;

import static com.example.inspire_app.R.*;
import static com.example.inspire_app.utils.App.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inspire_app.R;
import com.example.inspire_app.adapters.CommentRecyclerAdapter;
import com.example.inspire_app.fragments.HomeFragment;
import com.example.inspire_app.models.CommentData;
import com.example.inspire_app.models.DetailsModel;
import com.example.inspire_app.responsemodels.CommentResponse;
import com.example.inspire_app.responsemodels.GetDetailsResponse;
import com.example.inspire_app.responsemodels.PostResponse;
import com.example.inspire_app.viewmodels.CommentViewModel;
import com.example.inspire_app.viewmodels.GetDetailsViewModel;
import com.example.inspire_app.viewmodels.PostViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity {
    String _id;
    GetDetailsViewModel viewModel;
    List<DetailsModel> data = new ArrayList<>();
    TextView org,content;
    MaterialButton category;
    ImageView postimage;
    Boolean comments = false;
    RecyclerView recyclerView;
    CommentRecyclerAdapter adapter;
    TextView textView;
    LinearLayout commentsection;
    TextInputEditText textInputEditText;
    MaterialButton submit;
    String id,comment;
    CommentViewModel commentViewModel;
    List<String> arraylist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
        }
        else{
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Intent i = getIntent();
        _id = i.getStringExtra("id");
        org = findViewById(R.id.org);
        content = findViewById(R.id.details);
        category = findViewById(R.id.categorybtn);
        postimage = findViewById(R.id.postimage);
        recyclerView = findViewById(R.id.comment_recycler);
        textInputEditText = findViewById(R.id.comment_input);



        textView = findViewById(R.id.comment_text);
        commentsection = findViewById(R.id.comment_section);
        submit = findViewById(R.id.submitbtn);

        viewModel=new ViewModelProvider(PostActivity.this).get(GetDetailsViewModel.class);
        viewModel.btndetails(this.getApplication(),_id);
        viewModel.getCreateUserLiveData().observe(this, new Observer<GetDetailsResponse>() {
            @Override
            public void onChanged(GetDetailsResponse getDetailsResponse) {
                try {
                    if (getDetailsResponse.getData() == null) {
//
                        Toast.makeText(PostActivity.this, "Some Error Occured", Toast.LENGTH_SHORT).show();

                    } else {
                        org.setText(getDetailsResponse.getData().get(0).getOrganization());
                        content.setText(getDetailsResponse.getData().get(0).getContent());
                        category.setText(getDetailsResponse.getData().get(0).getCategory());
                        arraylist = getDetailsResponse.getData().get(0).getAllcomment();
                        comments = getDetailsResponse.getData().get(0).getComments();
                        id = getDetailsResponse.getData().get(0).get_id();
                        Picasso.with(PostActivity.this).load("http://192.168.1.105:3000" + getDetailsResponse.getData().get(0).getImageurl()).into(postimage);

                    }
                }catch (Exception e) {
                    Toast.makeText(PostActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                if(comments == true){
                    commentsection.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
                else{
                    commentsection.setVisibility(View.GONE);
                    textView.setText("No comments");
                }

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        comment = textInputEditText.getText().toString();
                        btncomment(comment,id);
                        viewModel.btndetails(PostActivity.this.getApplication(),_id);


                    }
                });

                try {
                    if (arraylist == null) {
//
                        Toast.makeText(getContext(), "Some Error Occured", Toast.LENGTH_SHORT).show();

                    } else {
                        adapter = new CommentRecyclerAdapter(PostActivity.this, arraylist);
                        recyclerView.setAdapter(adapter);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PostActivity.this);
                        recyclerView.setLayoutManager(linearLayoutManager);
                    }
                }catch (Exception e) {
                    Toast.makeText(PostActivity.this, "error", Toast.LENGTH_SHORT).show();
                }



            }
        });



    }
    private void btncomment(String comment,String id) {

        CommentData data = new CommentData(id,comment);
        initViewModel2();
        commentViewModel.btncomment(this.getApplication(), data);
    }

    private void initViewModel2() {
        commentViewModel = new ViewModelProvider(this).get(CommentViewModel.class);
        commentViewModel.getCreateUserLiveData().observe(this, new Observer<CommentResponse>() {
            @Override
            public void onChanged(CommentResponse commentResponse) {
                if (commentResponse == null) {
                    Toast.makeText(PostActivity.this, "Failed", Toast.LENGTH_SHORT).show();
//                    error.setText("Please enter correct OTP");
                } else {
                    textInputEditText.setText(" ");
                    Toast.makeText(PostActivity.this, commentResponse.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}