package com.sasken.notificationdemo;

import android.app.PendingIntent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.i(getString(R.string.DEBUG_TAG),"New Token : "+s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.i(getString(R.string.DEBUG_TAG),"Message Received");
        ((MyApplication)getApplication()).triggerNotificationForUpdation(NotificationDetailsActivity.class,getString(R.string.NEWS_CHANNEL_ID),
                remoteMessage.getNotification().getTitle(),
                remoteMessage.getNotification().getBody(),
                "This notification from FCM console ",
                NotificationCompat.PRIORITY_HIGH,
                true,
                getResources().getInteger(R.integer.notificationId),
                PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
