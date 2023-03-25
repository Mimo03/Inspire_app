package com.example.inspire_app.activites;

import static com.example.inspire_app.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.inspire_app.R;
import com.example.inspire_app.fragments.HomeFragment;
import com.example.inspire_app.models.DetailsModel;
import com.example.inspire_app.responsemodels.GetDetailsResponse;
import com.example.inspire_app.responsemodels.PostResponse;
import com.example.inspire_app.viewmodels.GetDetailsViewModel;
import com.example.inspire_app.viewmodels.PostViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity {
    String _id;
    GetDetailsViewModel viewModel;
    List<DetailsModel> data = new ArrayList<>();
    TextView org,content;
    MaterialButton category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
        }
        else{
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Intent i = getIntent();
        _id = i.getStringExtra("id");
        org = findViewById(R.id.org);
        content = findViewById(R.id.details);
        category = findViewById(R.id.categorybtn);

        viewModel=new ViewModelProvider(PostActivity.this).get(GetDetailsViewModel.class);
        viewModel.btndetails(this.getApplication(),_id);
        viewModel.getCreateUserLiveData().observe(this, new Observer<GetDetailsResponse>() {
            @Override
            public void onChanged(GetDetailsResponse getDetailsResponse) {
                org.setText(getDetailsResponse.getData().get(0).getOrganization());
                content.setText(getDetailsResponse.getData().get(0).getContent());
                category.setText(getDetailsResponse.getData().get(0).getCategory());




            }
        });

    }
}