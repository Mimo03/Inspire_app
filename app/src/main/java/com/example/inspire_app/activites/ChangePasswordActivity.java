package com.example.inspire_app.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.inspire_app.R;
import com.example.inspire_app.fragments.HomeFragment;
import com.example.inspire_app.models.ChangePassModel;
import com.example.inspire_app.models.CommentData;
import com.example.inspire_app.responsemodels.ChangePasswordResponse;
import com.example.inspire_app.responsemodels.CommentResponse;
import com.example.inspire_app.utils.LoginManager;
import com.example.inspire_app.viewmodels.ChangePasswordViewModel;
import com.example.inspire_app.viewmodels.CommentViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ChangePasswordActivity extends AppCompatActivity {

    TextInputEditText currPassword, newPassword, conPassword;
    TextInputLayout currentlayout, newlayout, confirmlayout;
    Button reset;
    String current, newPass, confirm;
    LoginManager loginManager;

    ChangePasswordViewModel changePasswordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);

        currPassword = findViewById(R.id.currPassword);
        newPassword = findViewById(R.id.newPassword);
        conPassword = findViewById(R.id.conPassword);
        reset = findViewById(R.id.resetButton);
        currentlayout = findViewById(R.id.text_input_layout_currPassword);
        newlayout = findViewById(R.id.text_input_layout_newPassword);
        confirmlayout = findViewById(R.id.text_input_layout_conPassword);

        loginManager = new LoginManager(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = currPassword.getText().toString();
                newPass = newPassword.getText().toString();
                confirm = conPassword.getText().toString();

                if( current.isEmpty()) {
                    currentlayout.setError("Enter Current Password");
                } else{
                    currentlayout.setError("");
                }
                if (newPass.isEmpty()) {
                    newlayout.setError("Enter New Password");
                } else{
                    newlayout.setError("");
                }
                if (confirm.isEmpty()) {
                    confirmlayout.setError("Enter Confirmed Password");
                } else{
                    confirmlayout.setError("");
                }

                if (!(current.isEmpty() && newPass.isEmpty() && confirm.isEmpty())){
                    if(current.equals(loginManager.getPassword())){
                        if(newPass.equals(confirm)){
                            btncomment(loginManager.getid(),confirm);

                        }else{
                            confirmlayout.setError("Password doesn't match");
                        }
                    }else{
                        currentlayout.setError("Enter Correct Password");
                    }
                }

            }
        });
    }

    private void btncomment(String _id,String password) {

        ChangePassModel data = new ChangePassModel(_id,password);
        initViewModel2();
        changePasswordViewModel.btnChangePass(this.getApplication(),data);
    }

    private void initViewModel2() {
        changePasswordViewModel = new ViewModelProvider(ChangePasswordActivity.this).get(ChangePasswordViewModel.class);
        changePasswordViewModel.getCreateUserLiveData().observe(ChangePasswordActivity.this, new Observer<ChangePasswordResponse>() {
            @Override
            public void onChanged(ChangePasswordResponse changePasswordResponse) {
                if (changePasswordResponse == null) {
                    Toast.makeText(ChangePasswordActivity.this, "Failed", Toast.LENGTH_SHORT).show();
//                    error.setText("Please enter correct OTP");
                } else {
                    onBackPressed();
                    Toast.makeText(ChangePasswordActivity.this, changePasswordResponse.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        });
    }


}
