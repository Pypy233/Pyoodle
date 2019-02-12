package nju.py.pyoodle.util;

/**
 * @Author: py
 * @Date: 2019/2/12 下午3:22
 * @Version 1.0
 */
import java.io.File;

public class FileUtil {
    private static String UPLOADED_FOLDER = "/Users/py/J2EEStrorage/";
    public static void createFolder(String prefix) {
        File file = new File(UPLOADED_FOLDER + prefix);
        if(!file.exists()) {
            file.mkdirs();
        }
    }


}
