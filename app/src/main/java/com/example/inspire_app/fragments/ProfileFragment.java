package com.example.inspire_app.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.inspire_app.R;
import com.example.inspire_app.activites.LoginActivity;
import com.example.inspire_app.activites.MainActivity;
import com.example.inspire_app.activites.SplashActivity;
import com.example.inspire_app.utils.LoginManager;
import com.google.android.material.button.MaterialButton;


public class ProfileFragment extends Fragment {
    MaterialButton logoutbtn,share;
    LoginManager loginManager;
    TextView name,moodleid;



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
        name = view.findViewById(R.id.setname);
        moodleid = view.findViewById(R.id.moodleid);
        loginManager = new LoginManager(getContext());


        name.setText(loginManager.getname());
        moodleid.setText(loginManager.getMoodleid());

        share = view.findViewById(R.id.sharebtn);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Hey, download this app!");
                startActivity(shareIntent);
            }
        });

        logoutbtn = view.findViewById(R.id.logoutbtn);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginManager.SetLoginStatus(false);
                loginManager.removeSharedPreference();

                startActivity(new Intent(getContext(), SplashActivity.class));
            }
        });
        return view;
    }
}