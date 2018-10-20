package com.trace;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.trace.framework.Node;

import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity {
    Node node;
    String fileStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileStr = FileUtils.getSDPath() + FileUtils.FILE_NAME;
        if (!FileUtils.fileCheck(fileStr)) {
            try {
                File file = new File(fileStr);
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Exception", e.toString());
            }
        }
        FileUtils.wirteFile(fileStr, "2018/09/12-14:45:50----onActivityCreated---(x,y)\r\n");
        FileUtils.wirteFile(fileStr, "2018/09/12-14:45:50----Activity_Action_Up---(x,y)\r\n");
        FileUtils.wirteFile(fileStr, "2018/09/12-14:45:50----Activity_Action_Up---(x,y)\r\n");
        FileUtils.wirteFile(fileStr, "2018/09/12-14:45:50----Activity_Action_Up---(x,y)\r\n");
//        getApplication().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
//            @Override
//            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
//                node.setClassLifeCycle(Node.LifeCycle.CREATE);
//            }
//
//            @Override
//            public void onActivityStarted(Activity activity) {
//                node.setClassLifeCycle(Node.LifeCycle.START);
//            }
//
//            @Override
//            public void onActivityResumed(Activity activity) {
//            }
//
//            @Override
//            public void onActivityPaused(Activity activity) {
//            }
//
//            @Override
//            public void onActivityStopped(Activity activity) {
//            }
//
//            @Override
//            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
//            }
//
//            @Override
//            public void onActivityDestroyed(Activity activity) {
//            }
//        });
//        gestureDetector = new GestureDetector(this, gestureProxy);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FileUtils.wirteFile(fileStr, "2018/09/12-14:45:50----onActivityStarted---(x,y)\r\n");
    }

    @Override
    protected void onResume() {
        super.onResume();
        FileUtils.wirteFile(fileStr, "2018/09/12-14:45:50----onActivityResumed---(x,y)\r\n");
    }

    @Override
    protected void onPause() {
        super.onPause();
        FileUtils.wirteFile(fileStr, "2018/09/12-14:45:50----onActivityPaused---(x,y)\r\n");
    }

    @Override
    protected void onStop() {
        super.onStop();
        FileUtils.wirteFile(fileStr, "2018/09/12-14:45:50----onActivityStopped---(x,y)\r\n");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FileUtils.wirteFile(fileStr, "2018/09/12-14:45:50----onActivityDestroyed---(x,y)\r\n");
    }

    //    GestureDetector gestureDetector;
//    GestureProxy gestureProxy;

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        return gestureDetector.onTouchEvent(event);
//    }
}
