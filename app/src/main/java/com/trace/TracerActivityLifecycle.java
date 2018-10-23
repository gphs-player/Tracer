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
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss   ", Locale.CHINESE);

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        getPermission(activity);
        FileUtils.CreateFile();
        FileUtils.wirteFile("LIFECYCLE::"+activity.getComponentName().flattenToString()+"::onCreated\n");

    }

    @Override
    public void onActivityStarted(Activity activity) {
        FileUtils.wirteFile("LIFECYCLE::"+activity.getComponentName().flattenToString()+"::Started\n");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        FileUtils.wirteFile("LIFECYCLE::"+activity.getComponentName().flattenToString()+"::Resumed\n");

    }

    @Override
    public void onActivityPaused(Activity activity) {
        FileUtils.wirteFile("LIFECYCLE::"+activity.getComponentName().flattenToString()+"::Paused\n");

    }

    @Override
    public void onActivityStopped(Activity activity) {
        FileUtils.wirteFile("LIFECYCLE::"+activity.getComponentName().flattenToString()+"::Stopped\n");

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        FileUtils.wirteFile("LIFECYCLE::"+activity.getComponentName().flattenToString()+"::Destroyed\n");

    }

    private void getPermission(Activity activity) {
        PermissionUtils.needPermission(activity, 10, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionUtils.OnPermissionListener() {
            @Override
            public void onPermissionGranted() {

            }

            @Override
            public void onPermissionDenied() {

            }
        });
    }
}
