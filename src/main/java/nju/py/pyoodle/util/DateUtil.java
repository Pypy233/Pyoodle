package nju.py.pyoodle.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @Author: py
 * @Date: 2019/2/14 下午11:44
 * @Version 1.0
 */
public class DateUtil {
    public static LocalDate parse(String text) {
        String sampleDate = "2019-02-28";
        if (text.length() == sampleDate.length()) {
            return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        String[] arr = text.split("-");
        int m = Integer.parseInt(arr[1]);
        int d = Integer.parseInt(arr[2]);
        String res = arr[0] + "-";
        if (m < 10) {
            res += "0" + arr[1];
        } else {
            res += arr[1];
        }
        if(d < 10) {
            res += "-0" + arr[2];
        } else {
            res += "-" + arr[2];
        }
        return LocalDate.parse(res, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static LocalDate getNow() {
        return LocalDate.now();
    }

//    public static void main(String[] args) {
////        System.out.println(DateUtil.parse("2018-12-2"));
////        System.out.println(DateUtil.parse("2018-1-24"));
////        System.out.println(DateUtil.parse("2018-1-2"));
//        System.out.println(DateUtil.getNow());
//    }


}
