package com.sasken.notificationdemo;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class MyApplication extends Application {

    MyAppNotificationManager myAppNotificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        myAppNotificationManager = MyAppNotificationManager.getInstance(this);
        myAppNotificationManager.registerNotificationChannel(
                getString(R.string.NEWS_CHANNEL_ID),
                getString(R.string.CHANNEL_NEWS),
                getString(R.string.CHANNEL_DESCRIPTION)
        );

        FirebaseMessaging.getInstance().setAutoInitEnabled(true);

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isSuccessful()){
                    Log.i(getString(R.string.DEBUG_TAG),"Task failed");
                    return;
                }
                Log.i(getString(R.string.DEBUG_TAG),"The result : "+task.getResult().getToken());
            }
        });
    }

    public void triggerNotification(Class targetNotificationActivity, String channelId, String title, String text, String bigtext,
                                    int priority, boolean autoCancel, int notificationId){
        myAppNotificationManager.triggerNotification(targetNotificationActivity,channelId,title,text,bigtext,priority,autoCancel,notificationId);
    }

    public void triggerNotificationForUpdation(Class targetNotificationActivity, String channelId, String title, String text, String bigtext,
                                    int priority, boolean autoCancel, int notificationId, int pendingIntentFlag){
        myAppNotificationManager.triggerNotificationForUpdation(targetNotificationActivity,channelId,title,text,bigtext,priority,autoCancel,notificationId,pendingIntentFlag);
    }

    public void triggerNotificationWithBackStack(Class targetNotificationActivity, String channelId, String title, String text, String bigtext,
                                               int priority, boolean autoCancel, int notificationId, int pendingIntentFlag){
        myAppNotificationManager.triggerNotificationWithBackStack(targetNotificationActivity,channelId,title,text,bigtext,priority,autoCancel,notificationId,pendingIntentFlag);
    }

    public void triggerOngoingNotification(Class targetNotificationActivity, String channelId, String title, String text, String bigtext,
                                           int priority,int notificationId, int pendingIntentFlag){
        myAppNotificationManager.triggerOngoingNotification(targetNotificationActivity,channelId,title,text,bigtext,priority,notificationId,pendingIntentFlag);
    }

    public void updateWithPicture(Class targetNotificationActivity, String channelId, String title, String text, String bigtext,
                                  int priority, boolean autoCancel, int notificationId){
        myAppNotificationManager.updateWithPicture(targetNotificationActivity,channelId,title,text,bigtext,priority,autoCancel,notificationId);
    }

    public void cancelNotification(int notificationId){
        myAppNotificationManager.cancelNotification(notificationId);
    }
}
