package com.example.inspire_app.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import com.example.inspire_app.R;
import com.example.inspire_app.adapters.NotificationRecyclerAdapter;
import com.example.inspire_app.fragments.HomeFragment;
import com.example.inspire_app.models.PostData;
import com.example.inspire_app.responsemodels.PostResponse;
import com.example.inspire_app.utils.LoginManager;
import com.example.inspire_app.viewmodels.PostViewModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "Message Channel";
    private static final int NOTIFICATION_ID = 100;
    private static final int REQ_ID = 100;
    NotificationRecyclerAdapter adapter;
    RecyclerView recyclerView;
    PostViewModel viewModel;
    List<PostData> data = new ArrayList<>();
    int count;
    List<PostData> newdata = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
        }
        else{
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        LoginManager loginManager = new LoginManager(this);


        viewModel=new ViewModelProvider(NotificationActivity.this).get(PostViewModel.class);
        viewModel.btnnewpost(this.getApplication(),"All",loginManager.getCategory());
        viewModel.getCreateUserLiveData().observe(this, new Observer<PostResponse>() {
            @Override
            public void onChanged(PostResponse postResponse) {
                data = postResponse.getData();
                count = data.size();
                if(count > loginManager.getcount()){
                    newdata.add(data.get(count - 1));
                    recyclerView = findViewById(R.id.notificationrecycler);
                    adapter = new NotificationRecyclerAdapter(NotificationActivity.this,newdata);
                    recyclerView.setAdapter(adapter);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NotificationActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);


                }
                else{
                    Toast.makeText(NotificationActivity.this,"less",Toast.LENGTH_SHORT).show();
                }

                Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.icon,null);
                //to convert drawable to bitmap drawable
                BitmapDrawable bitmapDrawable = (BitmapDrawable)drawable;
                //then we can get the bitmap from bitmapdrawable
                Bitmap largeIcon = bitmapDrawable.getBitmap();
                Notification notification;

                //whenever we want to access system service
                //here it will return the object of notification service
                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                //we create different channels so that the user can stop only that particular channel notification
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    notification = new Notification.Builder(NotificationActivity.this)
                            .setLargeIcon(largeIcon)
                            .setSmallIcon(R.drawable.icon)
                            .setContentText(data.get(count - 1).getOrganization())
                            .setSubText("New Post on "+data.get(count -1).getCategory()+" added")
                            .setChannelId(CHANNEL_ID)
                            .build();
                    nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID,"New Channel",NotificationManager.IMPORTANCE_HIGH));
                }

                else{
                    notification = new Notification.Builder(NotificationActivity.this)
                            .setLargeIcon(largeIcon)
                            .setSmallIcon(R.drawable.icon)
                            .setContentText(data.get(count - 1).getOrganization())
                            .setSubText("New Post on "+data.get(count -1).getCategory()+" added")
                            .build();

                }
                nm.notify(NOTIFICATION_ID,notification);

            }
        });








    }
}