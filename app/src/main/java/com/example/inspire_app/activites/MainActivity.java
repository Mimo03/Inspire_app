package com.example.inspire_app.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.inspire_app.R;
import com.example.inspire_app.adapters.HorzRecyclerAdapter;
import com.example.inspire_app.adapters.PostRecyclerAdapter;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    HorzRecyclerAdapter adapter;
    RecyclerView postrecycler;
    PostRecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.horizontalscroll);
        adapter = new HorzRecyclerAdapter(this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        postrecycler = findViewById(R.id.postrecyclerview);
        recyclerAdapter = new PostRecyclerAdapter(this);
        postrecycler.setAdapter(recyclerAdapter);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        postrecycler.setLayoutManager(linearLayoutManager1);


    }
}