package com.example.inspire_app.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inspire_app.R;
import com.example.inspire_app.models.LoginData;
import com.example.inspire_app.responsemodels.LoginResponse;
import com.example.inspire_app.utils.DebouncedOnClickListener;
import com.example.inspire_app.utils.LoginManager;
import com.example.inspire_app.viewmodels.LoginViewModel;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginButton;
    String moodleid,password2,moodle;
    LoginManager loginManager;
    LoginViewModel viewModel;
    String name,id;
    List<LoginData> data = new ArrayList<>();
    String token;
    String apipass;
    TextInputLayout textInputLayoutMoodleId;
    TextInputLayout textInputLayoutPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        textInputLayoutMoodleId = findViewById(R.id.text_input_layout_moodleId);
        textInputLayoutPassword = findViewById(R.id.text_input_layout_password);
        loginManager = new LoginManager(this);
        loginButton = findViewById(R.id.loginButton);


        loginButton.setOnClickListener(new DebouncedOnClickListener(800) {
            @Override
            public void onDebouncedClick(View v) {
                moodleid = username.getText().toString();
                password2 = password.getText().toString();
                if (moodleid.isEmpty()) {
                    textInputLayoutMoodleId.setError("Enter Moodle Id");
                }
                if (password2.isEmpty()) {
                    textInputLayoutPassword.setError("Enter Password");
                }
                else{
                    viewModel=new ViewModelProvider(LoginActivity.this).get(LoginViewModel.class);
                    viewModel.btnvalidate(LoginActivity.this.getApplication(),username.getText().toString(),token);
                    viewModel.getCreateUserLiveData().observe(LoginActivity.this, new Observer<LoginResponse>() {
                        @Override
                        public void onChanged(LoginResponse loginResponse) {
                            try {
                                if (loginResponse.getData() == null) {
//
                                    Toast.makeText(LoginActivity.this, "Some Error Occured", Toast.LENGTH_SHORT).show();

                                } else {

                                    data = loginResponse.getData();
                                    token = loginResponse.getToken();
                                    name = loginResponse.getData().get(0).getName();
                                    moodle = loginResponse.getData().get(0).getMoodleID();
                                    id = loginResponse.getData().get(0).get_id();
                                    apipass = loginResponse.getData().get(0).getPassword();
                                    if (apipass.equals(password2)) {
                                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(LoginActivity.this, ChooseCategoryActivity.class);
                                        i.putExtra("name", name);
                                        startActivity(i);
                                        loginManager.setname(name);
                                        loginManager.setMoodleid(moodle);
                                        loginManager.settoken(token);
                                        loginManager.setid(id);
                                        loginManager.setPassword(apipass);
                                        loginManager.SetLoginStatus(true);
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }catch (Exception e) {
                                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }


                        }

                    });


                }
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}