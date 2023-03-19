package com.example.inspire_app.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.inspire_app.R;
import com.example.inspire_app.utils.LoginManager;

public class SplashActivity extends AppCompatActivity {
    LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loginManager = new LoginManager(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(loginManager.isLoggedIn() == true){
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));


                }
                else{
                    Intent i = new Intent(SplashActivity.this,
                            LoginActivity.class);
                    startActivity(i);
                }
                finish();
            }
        },2000);    }


}