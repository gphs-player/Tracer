package com.trace;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.TextView;

import com.trace.framework.MyGestureDetectorListener;
import com.trace.framework.TracerUtils;
import com.trace.framework.WindowBehavior;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends Activity {
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 10;
    private final String TAG = "MainActivity";
    GestureDetectorCompat detector;
    private Random random;
    private Dialog dialog;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss   ", Locale.CHINESE);


    long timestamp = Long.MIN_VALUE;
    private TextView tv;
    private MyGestureDetectorListener detectorListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getPermission();
        TracerUtils.CreateFile();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        random = new Random();
        detectorListener = new MyGestureDetectorListener();
        detector = new GestureDetectorCompat(this, detectorListener);
        detector.setIsLongpressEnabled(false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        TracerUtils.writeKeyDown(keyCode);
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction()==MotionEvent.ACTION_DOWN){
            if (timestamp!=Long.MIN_VALUE) {
                float time = (System.currentTimeMillis()-timestamp)/1000.0f;
                TracerUtils.wirteFile("ACTION_MR::SLEEP::("+time+")\n");
            }
            timestamp = System.currentTimeMillis();
        }
        detector.onTouchEvent(ev);
        if (ev.getAction()==MotionEvent.ACTION_UP&&!detectorListener.getStack().isEmpty()){
            WindowBehavior pop = detectorListener.getStack().pop();
            float time = (System.currentTimeMillis()-timestamp)/1000.0f;
            TracerUtils.wirteFile("ACTION_MD::DRAG::(("+pop.x1+","+pop.y1+"),("+pop.x2+","+pop.y2+"),"+time+",10)\n");
            detectorListener.getStack().clear();
            timestamp = System.currentTimeMillis();
        }
        return super.dispatchTouchEvent(ev);
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

    private char getRandomChar() {
        String str = "";
        int hightPos; //
        int lowPos;

        hightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = (161 + Math.abs(random.nextInt(93)));

        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(hightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();

        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("错误");
        }

        return str.charAt(0);
    }

    private void getPermission() {
        PermissionUtils.needPermission(this, REQUEST_WRITE_EXTERNAL_STORAGE, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, new PermissionUtils.OnPermissionListener() {
            @Override
            public void onPermissionGranted() {
                TracerUtils.CreateFile();
            }

            @Override
            public void onPermissionDenied() {

            }
        });
    }



}
