package com.example.student.a20180109_02;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    String idLove = "LOVE";
    final int NOTIFICATION_ID=123123;
    NotificationChannel channelLove;
    NotificationManager nm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= 26)
        {
            regChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    public void regChannel()
    {
        channelLove = new NotificationChannel(
                idLove,
                "Channel Love",
                NotificationManager.IMPORTANCE_HIGH);
        channelLove.setDescription("最重要的人");
        channelLove.enableLights(true);
        channelLove.enableVibration(true);
        nm.createNotificationChannel(channelLove);
    }

    @TargetApi(Build.VERSION_CODES.O)
    @SuppressWarnings("deprecation")
    public void click1(View v)
    {
        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= 26)
        {
            builder = new Notification.Builder(MainActivity.this, idLove);
        }
        else
        {
            builder = new Notification.Builder(MainActivity.this);
        }

        Intent it = new Intent(MainActivity.this, InfoActivity.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 123, it,
                PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentTitle("測試");
        builder.setContentText("這是內容");
        if(Build.VERSION.SDK_INT >= 26) //左上角小圖的寬度因版本而異，所以也要分版本秀，不然會當機
        {
            builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        }
        else
        {
            builder.setSmallIcon(R.mipmap.ic_launcher);
        }

        builder.setAutoCancel(true);
        builder.setContentIntent(pi);

        Notification notify = builder.build();
        nm.notify(NOTIFICATION_ID, notify);

    }
    public void click2 (View v)
    {
        nm.cancel(NOTIFICATION_ID);
    }
}