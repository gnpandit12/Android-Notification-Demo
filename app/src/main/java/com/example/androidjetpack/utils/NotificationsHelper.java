package com.example.androidjetpack.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.androidjetpack.R;
import com.example.androidjetpack.StartActivity;

public class NotificationsHelper {

    private static NotificationsHelper instance;
    private Context mContext;
    private final String NOTIFICATION_CHANNELID = "CHANNEL_ID";
    private final int NOTIFICATION_ID = 12;

    public NotificationsHelper(Context context){
        this.mContext = context;
    }

    public static NotificationsHelper getInstance(Context context){
        if (instance == null){
            instance = new NotificationsHelper(context);
        }
        return instance;
    }

    public void createNotification(){
        createNotificationChannel();

        Intent intent = new Intent(mContext, StartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent,0 );
        Bitmap icon = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.pucho_hq);
        Notification notification =
                new NotificationCompat.Builder(mContext, NOTIFICATION_CHANNELID)
                        .setSmallIcon(R.drawable.pucho_hq)
                .setLargeIcon(icon)
                .setChannelId(NOTIFICATION_CHANNELID)
                .setContentTitle("Pucho Notification!")
                .setContentText("This is a notification from Pucho Technology!")
                .setContentIntent(pendingIntent)
                        .setStyle(
                                new NotificationCompat.BigPictureStyle()
                                        .bigPicture(icon)
                                        .bigLargeIcon(null)
                        )
                .build();
        NotificationManagerCompat.from(mContext)
                .notify(NOTIFICATION_ID, notification);
    }

    private void createNotificationChannel(){
        String channelName = NOTIFICATION_CHANNELID;
        int notificationImportance = NotificationManager.IMPORTANCE_DEFAULT;
        String channelDescription = "Description";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNELID, channelName, notificationImportance);
            notificationChannel.setDescription(channelDescription);
            notificationChannel.enableVibration(true);
            NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(mContext.NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }

}
