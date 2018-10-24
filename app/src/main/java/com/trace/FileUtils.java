package com.trace;

import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author guanzhong
 * @since 2018/10/20
 */
public class FileUtils {
    private static final int KEYCODE_BACK = 4;
    private static final int KEYCODE_ENTER = 66;
    private static final int KEYCODE_DEL = 67;
    private static FileUtils fileUtils = null;

    private FileUtils() {
    }

    public static FileUtils instance() {
        if (fileUtils == null) {
            synchronized (FileUtils.class) {
                if (fileUtils == null) {
                    fileUtils = new FileUtils();
                }
            }
        }
        return fileUtils;
    }

    private boolean fileCreate;
    private String fileName;

    public String getFileName() {
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
        if (FileUtils.instance().getFileCreate()) {
            return;
        }
        boolean created = false;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
        String date = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        String filePath = getSDPath() + DIRECTORY_NAME + FILE_NAME + date + FILE;
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
        FileUtils.instance().setFileName(filePath);
        FileUtils.instance().setFileCreate(created);
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
        if (!FileUtils.instance().getFileCreate()) {
            FileUtils.CreateFile();
            return;
        }
        long start = System.currentTimeMillis();
        Log.d("Time", "-------------------------");
        Log.d("Time", "start=" + System.currentTimeMillis());
        try {
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(FileUtils.instance().getFileName(), true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("wirteFile", e.toString());
        }
        Log.d("Time", "end=" + System.currentTimeMillis());
        Log.d("Time", "time=" + (System.currentTimeMillis() - start));
    }


    public static void writeKeyDown(int keyCode){
        switch (keyCode){
            case KEYCODE_BACK:
                FileUtils.wirteFile("ACTION_MD::PRESS::('KEYCODE_BACK',MonkeyDevice.DOWN_AND_UP)\n");
                break;
            case KEYCODE_ENTER:
                FileUtils.wirteFile("ACTION_MD::PRESS::('KEYCODE_ENTER',MonkeyDevice.DOWN_AND_UP)\n");
                break;
            case KEYCODE_DEL:
                FileUtils.wirteFile("ACTION_MD::PRESS::('KEYCODE_DEL',MonkeyDevice.DOWN_AND_UP)\n");
                break;
            default:
                break;
        }
    }
}