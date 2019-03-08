package nju.py.pyoodle.util;

/**
 * @Author: py
 * @Date: 2019/2/12 下午3:22
 * @Version 1.0
 */
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    private static final String UPLOADED_FOLDER = "/Users/py/J2EEStrorage/";
    private static final String LOCAL_PREFIX = "/J2EEStrorage/";

    public static void createFolder(String prefix) {
        File file = new File(UPLOADED_FOLDER + prefix);
        if(!file.exists()) {
            file.mkdirs();
        }
    }



    public static List<String> listPicName(String course) {
        String picPath = UPLOADED_FOLDER + course + "/pic";
        return listFileName(picPath);
    }

    public static List<String> listPPTName(String course) {
        String pptPath = UPLOADED_FOLDER + course + "/ppt";
        return listFileName(pptPath);
    }

    public static List<String> listHwName(String course) {
        String hwPath = UPLOADED_FOLDER + course + "/hw";
        return listFileName(hwPath);
    }

    public static List<String> listOnceNowHW(String coursePartName) {
        List<String> courseNameList = listFileName(UPLOADED_FOLDER);
        List<String> all = new ArrayList<>();
        for(String c: courseNameList) {
            if (c.contains(coursePartName) || c.equals(coursePartName) ) {
                all.add(c);
            }
        }
        return all;
    }


    public static void copyBase(String courseName, String courseBaseName) {
        File desDir = new File(UPLOADED_FOLDER + courseName);
        File srcDir = new File(UPLOADED_FOLDER + courseBaseName);
        try {
            FileUtils.copyDirectory(srcDir, desDir);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private static List<String> listFileName(String folderName) {

        File file = new File(folderName);
        List<String> list = new ArrayList<>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            assert files != null;
            for (File file1 : files) {
                if ( file1.isDirectory() ) {
                    return listFileName(file1.getPath());
                }
                if ( !file1.getName().startsWith(".") ) //兄dei，有隐藏.文件，剔掉
                    list.add(file1.getAbsolutePath().substring("/Users/py".length() + 1));
            }
        }
        return list;
    }



    public static void main(String[] args) {
        for (String s: listOnceNowHW("j")) {
            System.out.println(s);
        }
    }




}
