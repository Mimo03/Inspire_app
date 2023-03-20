package com.example.inspire_app.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inspire_app.R;
import com.example.inspire_app.utils.LoginManager;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginButton;
    LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        loginManager = new LoginManager(this);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().isEmpty()) {
                    username.setError("Enter Moodle Id");
                }
                if (password.getText().toString().isEmpty()) {
                    password.setError("Enter Password");
                }
                if (username.getText().toString().equals("20102119") && password.getText().toString().equals("1234")){
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    loginManager.SetLoginStatus(true);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this,"Login Successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this,"Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}