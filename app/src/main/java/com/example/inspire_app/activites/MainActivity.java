package com.example.inspire_app.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.example.inspire_app.R;
import com.example.inspire_app.adapters.HorzRecyclerAdapter;
import com.example.inspire_app.adapters.PostRecyclerAdapter;
import com.example.inspire_app.fragments.HomeFragment;
import com.example.inspire_app.fragments.LikeProfile;
import com.example.inspire_app.fragments.ProfileFragment;
import com.example.inspire_app.utils.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView btnView;
    Switch btnswitch;
    ImageView bellicon;
    String name,category;
    LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
        }
        else{
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        name = i.getStringExtra("name");
        category = i.getStringExtra("category");

        loginManager = new LoginManager(this);





        btnswitch = findViewById(R.id.btnSwitch);
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            btnswitch.setChecked(true);
        }

        btnswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    restartactivity();
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restartactivity();
                }
            }
        });

        bellicon = findViewById(R.id.bell);
        bellicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NotificationActivity.class));
            }
        });





        btnView = findViewById(R.id.bottomnav);

        btnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.nav_home){
                    giveFragment(new HomeFragment(),true);
                }
                else if(id == R.id.nav_search){
                    giveFragment(new LikeProfile(),false);
                }
                else{
                    giveFragment(new ProfileFragment(),false);
                }
                return true;
            }
        });
        btnView.setSelectedItemId(R.id.nav_home);
    }

    public void giveFragment(Fragment fragment, boolean flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
//        Bundle bundle = new Bundle();
//        bundle.putString("name",name);
//        fragment.setArguments(bundle);
        if(flag){
            ft.add(R.id.container2,fragment);
        }
        else{
            ft.replace(R.id.container2,fragment);
        }

        ft.commit();
    }
    public void restartactivity(){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }

}