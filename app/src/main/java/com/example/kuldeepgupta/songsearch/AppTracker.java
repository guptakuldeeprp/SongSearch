package com.example.kuldeepgupta.songsearch;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by kuldeep.gupta on 11/2/2016.
 */

public class AppTracker extends Application {

    private static Tracker mTracker;
    private static GoogleAnalytics analytics;


    @Override
    public void onCreate() {
        if (mTracker == null) {
            analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.global_tracker);
            mTracker.enableExceptionReporting(true);
            mTracker.enableAutoActivityTracking(true);
        }
        //return mTracker;
    }

    public static Tracker tracker() {
        return mTracker;
    }

    public static GoogleAnalytics analytics() {
        return analytics;
    }

}
