package com.manasikher.jobscheduler;

import android.annotation.SuppressLint;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class SensorService extends Service {

    public final int NOTIFICATION_ID_SERVICESAVE_FORGROUND = 1000;
    public static boolean SERVICE_RUNNING = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public synchronized int onStartCommand(Intent intent, int flags, int startId) {
        showMessage(getApplicationContext(),"hello");
        return startId;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @SuppressLint("NewApi")
    private void showMessage(Context context, String message) {
        NotificationBuilder builder = new NotificationBuilder();
        builder.setContext(context);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage(message);
        builder.setTitle("Foreground");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setPriority(NotificationBuilder.PRIORITY_DEFAULT);
        startForeground(NOTIFICATION_ID_SERVICESAVE_FORGROUND, builder.build());
    }

}
