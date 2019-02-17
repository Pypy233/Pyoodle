package nju.py.pyoodle.service;

import nju.py.pyoodle.domain.User;
import nju.py.pyoodle.util.Response;
import org.springframework.stereotype.Service;

/**
 * @Author: py
 * @Date: 2019/1/31 下午9:49
 * @Version 1.0
 */
@Service
public interface MailService {
    Response<Boolean> sendEmail(User user, String password, String email);

    /**
     * Generate and save for next use
     * @param user
     * @return
     */
    String generateConfirmationToken(User user);

    /**
     * Send all students in course
     * @param courseName
     * @param title
     * @param content
     * @return
     */
    Response<Boolean> sendAll(String courseName, String title, String content);

    boolean isActivate(String token);
}
