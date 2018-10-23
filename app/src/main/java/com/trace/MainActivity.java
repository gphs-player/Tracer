package com.trace;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.trace.framework.Node;

import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity {
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 10;

    Node node;
    String fileStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //申请权限和创建文件
        getPermission();
        FileUtils.CreateFile();


        fileStr = FileUtils.instance().getFileName();

        FileUtils.wirteFile(FileUtils.instance().getFileName(), "2018/09/12-14:45:50----onActivityCreated---(x,y)\r\n");

        FileUtils.wirteFile(fileStr, "2018/09/12-14:45:50----Activity_Action_Up---(x,y)\r\n");
        FileUtils.wirteFile(fileStr, "2018/09/12-14:45:50----Activity_Action_Up---(x,y)\r\n");
        FileUtils.wirteFile(fileStr, "2018/09/12-14:45:50----Activity_Action_Up---(x,y)\r\n");
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

    private void getPermission() {
        PermissionUtils.needPermission(this, REQUEST_WRITE_EXTERNAL_STORAGE, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, new PermissionUtils.OnPermissionListener() {
            @Override
            public void onPermissionGranted() {

            }

            @Override
            public void onPermissionDenied() {

            }
        });
    }
}
