package com.sasken.notificationdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.PendingIntent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button triggerNotificationButton,updateNotificationButton,cancelNotificationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        triggerNotificationButton = findViewById(R.id.btn_trigger_notification);
        triggerNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApplication)getApplication()).triggerOngoingNotification(NotificationDetailsActivity.class,getString(R.string.NEWS_CHANNEL_ID),
                        "Sample Notification",
                        "This is a sample notification",
                        "This is a sample notification created by Codetutor for demonstration of how to trigger notifications in Android app ",
                        NotificationCompat.PRIORITY_HIGH,
                        getResources().getInteger(R.integer.notificationId),
                        PendingIntent.FLAG_UPDATE_CURRENT);
            }
        });

        updateNotificationButton = findViewById(R.id.btn_update_notification);
        updateNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApplication)getApplication()).updateWithPicture(NotificationDetailsActivity.class, getString(R.string.NEWS_CHANNEL_ID),
                        "Updated Notification",
                        "This is a updated notification",
                        "This is a sample notification created by Codetutor for demonstration of how to trigger notifications in Android app ",
                        NotificationCompat.PRIORITY_HIGH,
                        true,
                        getResources().getInteger(R.integer.notificationId));
            }
        });

        cancelNotificationButton = findViewById(R.id.btn_cancel_notification);
        cancelNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApplication)getApplication()).cancelNotification(getResources().getInteger(R.integer.notificationId));
            }
        });
    }
}
