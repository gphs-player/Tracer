package com.trace;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Stack;

public class MainActivity extends Activity implements GestureDetector.OnGestureListener {
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 10;
    private final String TAG = "MainActivity";
    GestureDetectorCompat detector;
    private Random random;
    private Dialog dialog;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss   ", Locale.CHINESE);

    private Stack<Rect> stack = new Stack<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //申请权限和创建文件
        random = new Random();
        Button btn = findViewById(R.id.btn);
        ListView lv = findViewById(R.id.lv);
        detector = new GestureDetectorCompat(this, this);
        detector.setIsLongpressEnabled(false);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Dsafdasf", Toast.LENGTH_LONG).show();
                dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.view_text_show_dialog);
                dialog.show();
            }
        });
        List<String> names = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.setLength(0);
            sb.append(getRandomChar()).append(getRandomChar());
            names.add(sb.toString());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        lv.setAdapter(adapter);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyDown:" + event.getCharacters()+"\n"+keyCode);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        detector.onTouchEvent(ev);
        if (!stack.isEmpty()&&ev.getAction()==MotionEvent.ACTION_UP){
            Rect pop = stack.pop();
            FileUtils.wirteFile(sdf.format(new Date()) + "device.drag(("+pop.left+","+pop.top+"),("+pop.right+","+pop.bottom+"),1,1)\n");
            stack.clear();
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.d(TAG, "onDown:" + motionEvent.getX() + ":" + motionEvent.getY());
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        Log.d(TAG, "onShowPress:" + motionEvent.getX() + ":" + motionEvent.getY());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        FileUtils.wirteFile(sdf.format(new Date()) + "device.touch("+motionEvent.getX()+","+motionEvent.getY()+",MonkeyDevice.DOWN_AND_UP)\n");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent ev, MotionEvent ev1, float v, float v1) {
        stack.push(new Rect((int)ev.getX(),(int)ev.getY(),(int)ev1.getX(),(int)ev1.getY()));
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        Log.d(TAG, "onLongPress:" + motionEvent.getX() + ":" + motionEvent.getY());

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.d(TAG, "onFling:" + motionEvent.getX() + ":" + motionEvent.getY() + "\nonFling:" + motionEvent1.getX() + ":" + motionEvent1.getY() + "v:" + v + "v1:" + v1);
        return false;
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

            }

            @Override
            public void onPermissionDenied() {

            }
        });
    }
}
