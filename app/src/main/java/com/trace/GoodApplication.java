package com.trace;

import android.app.Application;
import android.app.Dialog;
import android.view.MotionEvent;

import com.trace.framework.TracerActivityLifecycle;
import com.trace.framework.TracerUtils;

import java.lang.reflect.Method;

/**
 * Created by pxw on 2018/10/23.
 */

public class GoodApplication extends Application implements Thread.UncaughtExceptionHandler {
    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(new TracerActivityLifecycle());

        Thread.setDefaultUncaughtExceptionHandler(this);
        try {
            Method method = Dialog.class.getMethod("dispatchTouchEvent", MotionEvent.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (e != null) {
            TracerUtils.wirteFile(e.getMessage());
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }


}

