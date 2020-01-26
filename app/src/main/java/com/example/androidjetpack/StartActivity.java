package com.example.androidjetpack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidjetpack.utils.NotificationsHelper;

public class StartActivity extends AppCompatActivity {

    private NotificationsHelper notificationsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        notificationsHelper = NotificationsHelper.getInstance(this);
        notificationsHelper.createNotification();

    }
}
