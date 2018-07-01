package com.manasikher.jobscheduler;

import android.annotation.SuppressLint;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.widget.Toast;

public class ServiceJobScheduler extends JobService {
    long lastUpdate;
    SensorManager sensorManager;

    @Override
    public boolean onStartJob(final JobParameters params) {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();
        Toast.makeText(getApplicationContext(), "working", Toast.LENGTH_LONG).show();
        NotificationForService(getApplicationContext());

        /*
        Return true if you want job to keep running
        example, if you have any thread doing processing
        you need to call jobFinsihed after thread finishes its task
        otherwise app will consume battery
        */
        return false;
    }

    @SuppressLint("NewApi")
    public void NotificationForService(Context context) {

        Intent serviceIntent = new Intent(context, SensorService.class);
        if (AndroidVersionUtil.isBeforeOreo()) {
            context.startService(serviceIntent);
        } else {
            context.startForegroundService(serviceIntent);
        }
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

}
