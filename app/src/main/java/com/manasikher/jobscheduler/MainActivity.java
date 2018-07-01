package com.manasikher.jobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private  UtilsJobSchedule smsJob;
    private JobInfo jobInfo;
    private JobScheduler jobScheduler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SensorService.class);
                stopService(intent);
                if (null != jobInfo) {
                    jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
                    jobScheduler.getAllPendingJobs();
                    jobScheduler.cancelAll();
                }
            }
        });
    }

    void startService() {
        smsJob = new UtilsJobSchedule();
        jobInfo = smsJob.createSmsJobSchedule(getApplicationContext(), "", 80);
        if (null != jobInfo) {
            jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            jobScheduler.schedule(jobInfo);
        }
    }
}
