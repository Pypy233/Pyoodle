package nju.py.pyoodle.service.Impl;

import nju.py.pyoodle.domain.User;
import nju.py.pyoodle.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @Author: py
 * @Date: 2019/1/31 下午10:09
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MailServiceImplTest {
    @Autowired
    private MailService mailService;
    @Test
    public void sendEmail() {
        User user = new User();
        user.setName("py");
        String lcx = "161250096@smail.nju.edu.cn";
        mailService.sendEmail(user, lcx);
    }

    @Test
    public void generateConfirmationToken() {
    }

    @Test
    public void isActivate() {
    }
}