package com.trace;

import android.app.Application;

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