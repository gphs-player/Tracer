package com.trace;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by pxw on 2018/10/23.
 */

public class GoodApplication extends Application implements Thread.UncaughtExceptionHandler {
    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(new TracerActivityLifecycle());
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (e != null) {
            FileUtils.wirteFile(e.getMessage());
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }


}

