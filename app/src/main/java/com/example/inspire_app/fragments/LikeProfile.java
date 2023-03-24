package com.example.inspire_app.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.inspire_app.R;
import com.example.inspire_app.activites.PostActivity;
import com.example.inspire_app.adapters.LikedPostRecyclerAdaptere;
import com.example.inspire_app.adapters.PostRecyclerAdapter;
import com.example.inspire_app.interfaces.LikedOnclickrecycler;
import com.example.inspire_app.interfaces.Postonclickrecyclerview;
import com.example.inspire_app.models.GetLiked;
import com.example.inspire_app.responsemodels.GetLikedResponse;
import com.example.inspire_app.responsemodels.PostResponse;
import com.example.inspire_app.viewmodels.LikedPostViewModel;
import com.example.inspire_app.viewmodels.PostViewModel;

import java.util.ArrayList;
import java.util.List;


public class LikeProfile extends Fragment {
    LikedPostRecyclerAdaptere adapter;
    RecyclerView recyclerView;
    LikedPostViewModel viewModel;
    List<GetLiked> data = new ArrayList<>();




    public LikeProfile() {
        // Required empty public constructor
    }


    public static LikeProfile newInstance(String param1, String param2) {
        LikeProfile fragment = new LikeProfile();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_like_profile, container, false);
        viewModel=new ViewModelProvider(LikeProfile.this).get(LikedPostViewModel.class);
        viewModel.btnnewpost(getActivity().getApplication());
        viewModel.getCreateUserLiveData().observe(getActivity(), new Observer<GetLikedResponse>() {
            @Override
            public void onChanged(GetLikedResponse getLikedResponse) {
                data = getLikedResponse.getData();
                recyclerView = view.findViewById(R.id.liked_recyclerview);
                adapter = new LikedPostRecyclerAdaptere(getContext(),data,new Postonclickrecyclerview() {
                    @Override
                    public void onclick() {
                        Intent intent = new Intent(getContext(), PostActivity.class);
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(adapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
            }
        });


        return view;
    }
}