package com.example.myapplication;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

public class NotificationService extends Service {

    private static final String CHANNEL_ID = "MyNotificationChannel";
    private static final int NOTIFICATION_ID = 1;

    public NotificationService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startForeground(NOTIFICATION_ID, createNotification());
    }

    private Notification createNotification() {
        Intent notificationIntent = new Intent(this, YourMainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            getSystemService(NotificationManager.class).createNotificationChannel(channel);
        }

        Notification.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(this, CHANNEL_ID)
                    .setContentTitle("Your App Name")
                    .setContentText("Values: [value1, value2, value3, value4]")
                    .setSmallIcon(R.drawable.ic_notification_icon)
                    .setContentIntent(pendingIntent);
        }

        return builder.build();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }
}
