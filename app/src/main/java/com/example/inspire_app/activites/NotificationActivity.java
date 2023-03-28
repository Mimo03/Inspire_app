package com.example.inspire_app.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.inspire_app.R;
import com.example.inspire_app.adapters.NotificationRecyclerAdapter;

public class NotificationActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "Message Channel";
    private static final int NOTIFICATION_ID = 100;
    private static final int REQ_ID = 100;
    NotificationRecyclerAdapter adapter;
    RecyclerView recyclerView;

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
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.icon)
                    .setContentText("New Message")
                    .setSubText("You may have a new message")
                    .setChannelId(CHANNEL_ID)
                    .build();
            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID,"New Channel",NotificationManager.IMPORTANCE_HIGH));
        }

        else{
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.icon)
                    .setContentText("New Message")
                    .setSubText("You may have a new message")
                    .build();

        }
        nm.notify(NOTIFICATION_ID,notification);

        recyclerView = findViewById(R.id.notificationrecycler);
        adapter = new NotificationRecyclerAdapter(this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}