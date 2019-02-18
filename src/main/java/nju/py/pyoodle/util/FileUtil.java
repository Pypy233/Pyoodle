package nju.py.pyoodle.util;

/**
 * @Author: py
 * @Date: 2019/2/12 下午3:22
 * @Version 1.0
 */
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    private static String UPLOADED_FOLDER = "/Users/py/J2EEStrorage/";

    public static void createFolder(String prefix) {
        File file = new File(UPLOADED_FOLDER + prefix);
        if(!file.exists()) {
            file.mkdirs();
        }
    }



    public static List<String> listPicName(String course) {
        String picPath = UPLOADED_FOLDER + "/" + course + "/pic";
        return listFileName(picPath);
    }

    public static List<String> listPPTName(String course) {
        String pptPath = UPLOADED_FOLDER + "/" + course + "/ppt";
        return listFileName(pptPath);
    }

    public static List<String> listHwName(String course) {
        String hwPath = UPLOADED_FOLDER + "/" + course + "/hw";
        return listFileName(hwPath);
    }


    private static List<String> listFileName(String folderName) {

        File file = new File(folderName);
        List<String> list = new ArrayList<>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    return listFileName(files[i].getPath());
                }
                if (!files[i].getName().startsWith(".")) //兄dei，有隐藏.文件，剔掉
                    list.add(files[i].getName());
            }
        }
        return list;
    }

    public static void main(String[] args) {
       FileUtil.createFolder("数据结构/s/");
    }




}