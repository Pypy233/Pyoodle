package nju.py.pyoodle.util;

import nju.py.pyoodle.enumeration.UserType;

/**
 * @Author: py
 * @Date: 2019/2/1 下午5:09
 * @Version 1.0
 */
public class IdentityUtil {
    public static UserType getUserType(String email) {
        String[] num = email.split("@");
        char[] numArr = num[0].toCharArray();
        for(char c: numArr) {
            if(!Character.isDigit(c)) {
                return UserType.TEACHER;
            }
        }
        return UserType.UNDERGRADUATE;
    }

    public static String getNumber(String email) {
        String[] num = email.split("@");
        return num[0];
    }
}
