package com.example.inspire_app.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.inspire_app.R;
import com.example.inspire_app.utils.LoginManager;
import com.google.android.material.button.MaterialButton;

public class ChooseCategoryActivity extends AppCompatActivity {
    MaterialButton startup,internship,hackathon,achievement,eventwinners;
    LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);

        loginManager = new LoginManager(this);

        startup = findViewById(R.id.startup);
        startup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChooseCategoryActivity.this,MainActivity.class);
                i.putExtra("category","Startup");
                loginManager.setCategory("Startup");
                startActivity(i);
            }
        });
        internship = findViewById(R.id.internship);
        internship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChooseCategoryActivity.this,MainActivity.class);
                i.putExtra("category","Internship");
                loginManager.setCategory("Internship");
                startActivity(i);

            }
        });
        hackathon = findViewById(R.id.hackathon);
        hackathon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChooseCategoryActivity.this,MainActivity.class);
                i.putExtra("category","Hackathon");
                loginManager.setCategory("Hackathon");
                startActivity(i);
            }
        });
        achievement = findViewById(R.id.achievement);
        achievement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChooseCategoryActivity.this,MainActivity.class);
                i.putExtra("category","Achievement");
                loginManager.setCategory("Achievement");
                startActivity(i);
            }
        });
        eventwinners = findViewById(R.id.eventwinner);
        eventwinners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChooseCategoryActivity.this,MainActivity.class);
                i.putExtra("category","Event Winners");
                loginManager.setCategory("Event Winners");
                startActivity(i);
            }
        });


    }
}