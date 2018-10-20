package com.trace;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.trace.framework.GestureProxy;
import com.trace.framework.Node;

public class MainActivity extends AppCompatActivity {

    //试一下，请忽略
    Node node;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getApplication().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                node.setClassLifeCycle(Node.LifeCycle.CREATE);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                node.setClassLifeCycle(Node.LifeCycle.START);
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
        gestureDetector = new GestureDetector(this, gestureProxy);
    }

    GestureDetector gestureDetector;
    GestureProxy gestureProxy;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}
