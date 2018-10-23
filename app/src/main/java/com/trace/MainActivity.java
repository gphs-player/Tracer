package com.trace;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.trace.framework.Node;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity implements GestureDetector.OnGestureListener{
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 10;
    private final String TAG = "MainActivity";
    GestureDetectorCompat detector;
    private Random random;
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


        random = new Random();
        Button btn = findViewById(R.id.btn);
        ListView lv = findViewById(R.id.lv);
        detector = new GestureDetectorCompat(this,this);
        detector.setIsLongpressEnabled(false);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Dsafdasf",Toast.LENGTH_LONG).show();
            }
        });
        List<String> names = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.setLength(0);
            sb.append(getRandomChar()).append(getRandomChar());
            names.add(sb.toString());
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,names);
        lv.setAdapter(adapter);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        detector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.d(TAG,"onDown:"+motionEvent.getX()+":"+motionEvent.getY());
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        Log.d(TAG,"onShowPress:"+motionEvent.getX()+":"+motionEvent.getY());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        Log.d(TAG,"onSingleTapUp:"+motionEvent.getX()+":"+motionEvent.getY());
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {

        Log.d(TAG,"onScroll:"+motionEvent.getX()+":"+motionEvent.getY()+"\nonScroll:"+motionEvent1.getX()+":"+motionEvent1.getY()+"v:"+v+"v1:"+v1);
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        Log.d(TAG,"onLongPress:"+motionEvent.getX()+":"+motionEvent.getY());

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.d(TAG,"onFling:"+motionEvent.getX()+":"+motionEvent.getY()+"\nonFling:"+motionEvent1.getX()+":"+motionEvent1.getY()+"v:"+v+"v1:"+v1);
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
