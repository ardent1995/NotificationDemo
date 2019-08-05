package com.sasken.notificationdemo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.TaskStackBuilder;

class MyAppNotificationManager {

    private Context context;
    private static MyAppNotificationManager instance;
    private NotificationManagerCompat notificationManagerCompat;
    private NotificationManager notificationManager;

    private MyAppNotificationManager(Context context){
        this.context = context;
        notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public static MyAppNotificationManager getInstance(Context context){
        if(instance == null){
            instance = new MyAppNotificationManager(context);
        }
        return instance;
    }

    public void registerNotificationChannel(String channelId, String channelName, String channelDescription){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(channelId,channelName,NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(channelDescription);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void triggerNotification(Class targetNotificationActivity, String channelId, String title, String text, String bigtext,
                                    int priority, boolean autoCancel, int notificationId){
        Intent intent = new Intent(context, targetNotificationActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,channelId)
                .setSmallIcon(R.drawable.ic_audiotrac)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_icon_large))
                .setContentTitle(title)
                .setContentText(text)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(bigtext))
                .setPriority(priority)
                .setContentIntent(pendingIntent)
                .setAutoCancel(autoCancel);

        notificationManagerCompat.notify(notificationId,builder.build());
    }

    public void triggerNotificationForUpdation(Class targetNotificationActivity, String channelId, String title, String text, String bigtext,
                                                 int priority, boolean autoCancel, int notificationId, int pendingIntentFlag){
        Intent intent = new Intent(context, targetNotificationActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,pendingIntentFlag);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,channelId)
                .setSmallIcon(R.drawable.ic_audiotrac)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_icon_large))
                .setContentTitle(title)
                .setContentText(text)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(bigtext))
                .setPriority(priority)
                .setContentIntent(pendingIntent)
                .setAutoCancel(autoCancel);

        notificationManagerCompat.notify(notificationId,builder.build());
    }

    public void triggerNotificationWithBackStack(Class targetNotificationActivity, String channelId, String title, String text, String bigtext,
                                    int priority, boolean autoCancel, int notificationId, int pendingIntentFlag){
        Intent intent = new Intent(context, targetNotificationActivity);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
        taskStackBuilder.addNextIntentWithParentStack(intent);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0,pendingIntentFlag);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,channelId)
                .setSmallIcon(R.drawable.ic_audiotrac)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_icon_large))
                .setContentTitle(title)
                .setContentText(text)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(bigtext))
                .setPriority(priority)
                .setContentIntent(pendingIntent)
                .setAutoCancel(autoCancel);

        notificationManagerCompat.notify(notificationId,builder.build());
    }

    public void triggerOngoingNotification(Class targetNotificationActivity, String channelId, String title, String text, String bigtext,
                                                 int priority, int notificationId, int pendingIntentFlag){
        Intent intent = new Intent(context, targetNotificationActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,pendingIntentFlag);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,channelId)
                .setSmallIcon(R.drawable.ic_audiotrac)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_icon_large))
                .setContentTitle(title)
                .setContentText(text)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(bigtext))
                .setPriority(priority)
                .setContentIntent(pendingIntent)
                .setOngoing(true);

        notificationManagerCompat.notify(notificationId,builder.build());
    }

    public void updateWithPicture(Class targetNotificationActivity, String channelId, String title, String text, String bigtext,
                                  int priority, boolean autoCancel, int notificationId){
        Intent intent = new Intent(context, targetNotificationActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,channelId)
                .setSmallIcon(R.drawable.ic_audiotrac)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_icon_large))
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(priority)
                .setContentIntent(pendingIntent)
                .setAutoCancel(autoCancel);
        Bitmap androidImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.big_pic);
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(androidImage).setBigContentTitle(bigtext));
        notificationManagerCompat.notify(notificationId,builder.build());
    }

    public void cancelNotification(int notificationId){
        notificationManagerCompat.cancel(notificationId);
    }
}
