package com.trace;

import android.Manifest;
import android.app.Application;
import android.view.WindowManager;

/**
 * Created by pxw on 2018/10/23.
 */

public class GoodApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new TracerActivityLifecycle());
    }

}
