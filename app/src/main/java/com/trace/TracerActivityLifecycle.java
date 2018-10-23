package com.trace;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by pxw on 2018/10/23.
 */

public class TracerActivityLifecycle implements Application.ActivityLifecycleCallbacks {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss   ", Locale.CHINESE);
    private final String TAG = "MainActivity";

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        getPermission(activity);
        FileUtils.CreateFile();
        FileUtils.wirteFile(sdf.format(new Date())+activity.getComponentName().getClassName()+"   onActivityCreated\n");

    }

    @Override
    public void onActivityStarted(Activity activity) {
        FileUtils.wirteFile(sdf.format(new Date())+activity.getComponentName().getClassName()+"   onActivityStarted\n");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        FileUtils.wirteFile(sdf.format(new Date())+activity.getComponentName().getClassName()+"   onActivityResumed\n");

    }

    @Override
    public void onActivityPaused(Activity activity) {
        FileUtils.wirteFile(sdf.format(new Date())+activity.getComponentName().getClassName()+"   onActivityPaused\n");

    }

    @Override
    public void onActivityStopped(Activity activity) {
        FileUtils.wirteFile(sdf.format(new Date())+activity.getComponentName().getClassName()+"   onActivityStopped\n");

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        FileUtils.wirteFile(sdf.format(new Date())+activity.getComponentName().getClassName()+"   onActivityDestroyed\n");

    }
    private void getPermission(Activity activity) {
        PermissionUtils.needPermission(activity, 10, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, new PermissionUtils.OnPermissionListener() {
            @Override
            public void onPermissionGranted() {

            }

            @Override
            public void onPermissionDenied() {

            }
        });
    }
}
