/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		STAAndroidVersionUtil.java
 * @Project:
 *		Stardom
 * @Abstract:
 *
 * @Copyright:
 *     		Copyright © 2012-2013, Viacom 18 Media Pvt. Ltd
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/*! Revision history (Most recent first)
 Created by pankaj on 02-Jan-2014
 */

package com.manasikher.jobscheduler;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;

public class AndroidVersionUtil {

    public static int getAndroidVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static boolean isBeforeOreo() {
        return (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) ? true : false;
    }

    public static boolean isBeforeMarshmallow() {
        return (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) ? true : false;
    }

    public static String getUniqueDeviceIdentification(Context context) {


        /*
         * Combination of deviceId,AndroidId and SerialNumber
         */

        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);

        StringBuilder sb = new StringBuilder();

        sb.append(TextUtils.isEmpty(telephonyManager.getDeviceId()) ? ""
                : telephonyManager.getDeviceId());

        sb.append(TextUtils.isEmpty(Settings.Secure.getString(
                context.getContentResolver(), Settings.Secure.ANDROID_ID)) ? ""
                : Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID));

        sb.append(TextUtils.isEmpty(Build.SERIAL) ? ""
                : Build.SERIAL);

        return sb.toString();

    }

    public static int getVersionCode(Context context) {

        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return info.versionCode;
    }

    public static String getVersionName(Context context) {

        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return info != null ? info.versionName : "0.0.0";
    }

    public static float converPixelsToSp(float px, Context context) {
        return px / context.getResources().getDisplayMetrics().scaledDensity;
    }

    public static boolean isTouchOutsideInitialPosition(MotionEvent event, View view) {
        return event.getX() > view.getX() + view.getWidth();
    }


    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;


        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }


}
