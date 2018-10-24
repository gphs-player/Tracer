package com.trace.framework;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author guanzhong
 * @since 2018/10/20
 */
public class TracerUtils {
    private static final int KEYCODE_BACK = 4;
    private static final int KEYCODE_ENTER = 66;
    private static final int KEYCODE_DEL = 67;
    private static TracerUtils tracerUtils = null;

    private TracerUtils() {
    }

    public static TracerUtils instance() {
        if (tracerUtils == null) {
            synchronized (TracerUtils.class) {
                if (tracerUtils == null) {
                    tracerUtils = new TracerUtils();
                }
            }
        }
        return tracerUtils;
    }

    private boolean fileCreate;
    private String fileName;

    private String getFileName() {
        return fileName;
    }

    private void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private boolean getFileCreate() {
        return fileCreate;
    }

    private void setFileCreate(boolean fileCreate) {
        this.fileCreate = fileCreate;
    }

    public static void CreateFile() {
        if (TracerUtils.instance().getFileCreate()) {
            return;
        }
        boolean created = false;
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
//        String date = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        String filePath = getSDPath() + DIRECTORY_NAME + FILE_NAME + "tracer" + FILE;
        try {
            File file = new File(filePath);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                if (!parentFile.mkdirs()) {
                    return;
                }
            }
            if (!file.exists()) {
                created = file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        TracerUtils.instance().setFileName(filePath);
        TracerUtils.instance().setFileCreate(created);
    }

    private static final String DIRECTORY_NAME = "/SLog/";
    private static final String FILE_NAME = "log_";
    private static final String FILE = ".txt";

    private static String getSDPath() {
        File sdDir = null;
        // 判断sd卡是否存在
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            // 获取根目录
            sdDir = Environment.getExternalStorageDirectory();
        }
        return sdDir == null ? "" : sdDir.getAbsolutePath() + "";
    }

    /**
     * 追加文件：使用FileWriter
     */
    public static void wirteFile(String content) {
        if (!TracerUtils.instance().getFileCreate()) {
            TracerUtils.CreateFile();
            return;
        }
        long start = System.currentTimeMillis();
        Log.d("Time", "-------------------------");
        Log.d("Time", "start=" + System.currentTimeMillis());
        try {
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(TracerUtils.instance().getFileName(), true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("wirteFile", e.toString());
        }
        Log.d("Time", "end=" + System.currentTimeMillis());
        Log.d("Time", "time=" + (System.currentTimeMillis() - start));
    }


    public static void writeKeyDown(int keyCode) {
        switch (keyCode) {
            case KEYCODE_BACK:
                TracerUtils.wirteFile("ACTION_MD::PRESS::('KEYCODE_BACK',MonkeyDevice.DOWN_AND_UP)\n");
                break;
            case KEYCODE_ENTER:
                TracerUtils.wirteFile("ACTION_MD::PRESS::('KEYCODE_ENTER',MonkeyDevice.DOWN_AND_UP)\n");
                break;
            case KEYCODE_DEL:
                TracerUtils.wirteFile("ACTION_MD::PRESS::('KEYCODE_DEL',MonkeyDevice.DOWN_AND_UP)\n");
                break;
            default:
                break;
        }
    }

    public static void startAccessibilityService(final Activity activity) {
        new AlertDialog.Builder(activity)
                .setTitle("开启辅助功能")
                .setMessage("使用此项功能需要您开启辅助功能")
                .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 隐式调用系统设置界面
                        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                        activity.startActivity(intent);
                    }
                }).create().show();
    }
}