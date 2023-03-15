package com.example.inspire_app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inspire_app.R;
import com.example.inspire_app.adapters.HorzRecyclerAdapter;
import com.example.inspire_app.adapters.PostRecyclerAdapter;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    HorzRecyclerAdapter adapter;
    RecyclerView postrecycler;
    PostRecyclerAdapter recyclerAdapter;


    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.horizontalscroll);
        adapter = new HorzRecyclerAdapter(this.getContext());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        postrecycler = view.findViewById(R.id.postrecyclerview);
        recyclerAdapter = new PostRecyclerAdapter(this.getContext());
        postrecycler.setAdapter(recyclerAdapter);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this.getContext());
        postrecycler.setLayoutManager(linearLayoutManager1);
        return view;
    }
}