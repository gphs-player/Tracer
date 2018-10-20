package com.trace;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author guanzhong
 * @since 2018/10/20
 */
public class FileUtils {
    public static final String FILE_NAME = "/Logs_00001.txt";

    public static String getSDPath() {
        File sdDir = null;
        // 判断sd卡是否存在
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            // 获取根目录
            sdDir = Environment.getExternalStorageDirectory();
        }
        return sdDir == null ? "" : sdDir.toString();
    }

    public static boolean fileCheck(String fileName){
        File file = new File(fileName);
        return file.exists();
    }

    /**
     * 追加文件：使用FileWriter
     */
    public static void wirteFile(String fileName, String content) {
        long start = System.currentTimeMillis();
        Log.e("Time", "-------------------------");
        Log.e("Time", "start=" + System.currentTimeMillis());
        try {
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("wirteFile", e.toString());
        }
        Log.e("Time", "end=" + System.currentTimeMillis());
        Log.e("Time", "time=" + (System.currentTimeMillis() - start));
    }
}
