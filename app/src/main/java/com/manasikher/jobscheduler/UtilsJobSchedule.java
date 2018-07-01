package com.manasikher.jobscheduler;


import android.app.job.JobInfo;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;
import android.util.Log;


public class UtilsJobSchedule {

    public JobInfo createSmsJobSchedule(Context mContext, String message, long delayInMillis) {
        PersistableBundle bundle = new PersistableBundle();
        bundle.putInt("JOBID", 1001);
        bundle.putString("MESSAGE", message);
        bundle.putInt("JOBTYPE", 101);
        bundle.putString("TIMEDELAY", String.valueOf(delayInMillis));

        JobInfo.Builder builder = new JobInfo.Builder(1001, new ComponentName(mContext, ServiceJobScheduler.class));
        builder.setMinimumLatency(delayInMillis);
        builder.setOverrideDeadline(delayInMillis);
        builder.setExtras(bundle);
        JobInfo jobInfo = builder.build();
        Log.d("WASTE", "JobInfo:" + jobInfo.toString() + " delayInMillis: " + delayInMillis);

        return jobInfo;
    }

}
