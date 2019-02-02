package nju.py.pyoodle.service.Impl;

import nju.py.pyoodle.dao.UserDAO;
import nju.py.pyoodle.domain.User;
import nju.py.pyoodle.service.MailService;
import nju.py.pyoodle.util.IdentityUtil;
import nju.py.pyoodle.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.UUID;

/**
 * @Author: py
 * @Date: 2019/1/31 下午9:54
 * @Version 1.0
 */
@Component
public class MailServiceImpl implements MailService {
    private final UserDAO userDAO;

    private final static String SENDER = "2529716798@qq.com";
    private final static String PWD = "xdiglmveggqudhif";

    @Autowired
    public MailServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public Response<Boolean> sendEmail(User user, String password, String email) {
        try {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.qq.com");
            mailSender.setUsername(SENDER);
            mailSender.setPassword(PWD);

            MimeMessage mailMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage,true,"utf8");
            helper.setFrom(mailSender.getUsername());
            helper.setTo(email);
            helper.setSubject("Moodle身份认证");
            helper.setText("邮件发送成功");
            String emailToken = generateConfirmationToken(user);
            String url = "<a href='http://localhost:8080/register/activate?emailToken="+emailToken+"'>激活"+"" +
                    "</a></br><h1>如果以上超连接无法访问，请将以下网址复制到浏览器地址栏中</h1>" +
                    "<h2>http://localhost:8080/register/activate?emailToken="+emailToken+"</h2>";
            helper.setText(url, true);
            mailSender.send(mailMessage);
            user.setType(IdentityUtil.getUserType(email));
            user.setStudentNumber(IdentityUtil.getNumber(email));
            user.setPassword(password);
            user.setEmail(email);
            userDAO.save(user);
            return new Response<>(true, "Succeed to send email");
        }catch (Exception e){
            e.printStackTrace();
            return new Response<>(false, "Fail to send message");
        }
    }

    @Override
    public String generateConfirmationToken(User user) {
        String token = UUID.randomUUID().toString();
        user.setConfirmationToken(token);
        userDAO.save(user);
        return token;
    }

    @Override
    public boolean isActivate(String token) {
        User user = userDAO.getUserByConfirmationToken(token);
        if (user != null) {
            user.setEnabled(1);
            userDAO.save(user);
            return true;
        }
        return false;
    }
}
