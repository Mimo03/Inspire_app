package com.example.inspire_app.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.inspire_app.R;
import com.example.inspire_app.adapters.HorzRecyclerAdapter;
import com.example.inspire_app.adapters.PostRecyclerAdapter;
import com.example.inspire_app.fragments.HomeFragment;
import com.example.inspire_app.fragments.LikeProfile;
import com.example.inspire_app.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





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
//        btnView.setSelectedItemId(R.id.nav_home);
    }

    public void giveFragment(Fragment fragment, boolean flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(flag){
            ft.add(R.id.container2,fragment);
        }
        else{
            ft.replace(R.id.container2,fragment);
        }

        ft.commit();
    }

}