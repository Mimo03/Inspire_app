package com.example.inspire_app.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inspire_app.R;
import com.example.inspire_app.activites.LoginActivity;
import com.example.inspire_app.activites.MainActivity;
import com.example.inspire_app.activites.SplashActivity;
import com.example.inspire_app.utils.LoginManager;
import com.google.android.material.button.MaterialButton;


public class ProfileFragment extends Fragment {
    MaterialButton logoutbtn;
    LoginManager loginManager;



    public ProfileFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        logoutbtn = view.findViewById(R.id.logoutbtn);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginManager = new LoginManager(getContext());
                loginManager.SetLoginStatus(false);

                loginManager.removeSharedPreference();

                startActivity(new Intent(getContext(), SplashActivity.class));
            }
        });
        return view;
    }
}