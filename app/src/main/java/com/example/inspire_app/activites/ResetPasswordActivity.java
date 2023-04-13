package com.example.inspire_app.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.inspire_app.R;
import com.example.inspire_app.models.ChangePassModel;
import com.example.inspire_app.models.ResetPassModel;
import com.example.inspire_app.responsemodels.ChangePasswordResponse;
import com.example.inspire_app.responsemodels.ResetPassResponse;
import com.example.inspire_app.utils.LoginManager;
import com.example.inspire_app.viewmodels.ChangePasswordViewModel;
import com.example.inspire_app.viewmodels.ResetPassViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class ResetPasswordActivity extends AppCompatActivity {

    TextInputEditText newPassword, conPassword;
    TextInputLayout newPasslayout, conPasslayout;
    Button reset;
    String newPassvalue, conPassvalue;
    LoginManager loginManager;
    ResetPassViewModel resetPassViewModel;
    String moodleID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        newPassword = findViewById(R.id.newPassword);
        conPassword = findViewById(R.id.conPassword);
        newPasslayout = findViewById(R.id.text_input_layout_newPassword);
        conPasslayout = findViewById(R.id.text_input_layout_conPassword);
        reset = findViewById(R.id.resetButton);

        Intent intent = getIntent();
        moodleID = intent.getStringExtra("moodleID");


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPassvalue = newPassword.getText().toString();
                conPassvalue = conPassword.getText().toString();

                if(newPassvalue.isEmpty()){
                    newPasslayout.setError("Enter New Password");
                }
                if(conPassvalue.isEmpty()){
                    conPasslayout.setError("Enter Confirmed Password");
                }
                else{
                    if(newPassvalue.equals(conPassvalue)){
                        btncomment(moodleID, conPassvalue);
                        Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    else{
                        conPasslayout.setError("Password doesn't match");
                    }
                }
            }
        });

    }

    private void btncomment(String moodleID,String password) {

        ResetPassModel data = new ResetPassModel(moodleID,password);
        initViewModel2();
        resetPassViewModel.btnChangePass(this.getApplication(),data);
    }

    private void initViewModel2() {
        resetPassViewModel = new ViewModelProvider(ResetPasswordActivity.this).get(ResetPassViewModel.class);
        resetPassViewModel.getCreateUserLiveData().observe(ResetPasswordActivity.this, new Observer<ResetPassResponse>() {
            @Override
            public void onChanged(ResetPassResponse changePasswordResponse) {
                if (changePasswordResponse == null) {
                    Toast.makeText(ResetPasswordActivity.this, "Failed", Toast.LENGTH_SHORT).show();
//                    error.setText("Please enter correct OTP");
                } else {
                    Toast.makeText(ResetPasswordActivity.this, changePasswordResponse.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

}
