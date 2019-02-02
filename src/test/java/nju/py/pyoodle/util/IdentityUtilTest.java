package nju.py.pyoodle.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author: py
 * @Date: 2019/2/1 下午5:53
 * @Version 1.0
 */
public class IdentityUtilTest {

    @Test
    public void getUserType() {
        System.out.println(IdentityUtil.getUserType("16@smail.nju.edu.cn"));
    }

    @Test
    public void getNumber() {
        System.out.println(IdentityUtil.getNumber("16@smail.nju.edu.cn"));
    }
}