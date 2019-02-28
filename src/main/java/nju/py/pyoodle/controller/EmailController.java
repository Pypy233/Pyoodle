package nju.py.pyoodle.controller;

import nju.py.pyoodle.dao.UserDAO;
import nju.py.pyoodle.domain.User;
import nju.py.pyoodle.service.MailService;
import nju.py.pyoodle.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: py
 * @Date: 2019/1/31 下午11:12
 * @Version 1.0
 */
@Controller
public class EmailController {
    private final MailService mailService;

    @Autowired
    public EmailController(MailService mailService, UserDAO userDAO) {
        this.mailService = mailService;
    }

    @PostMapping(value = "/register/mail")
    @ResponseBody
    public Response<Boolean> sendEmail(String username, String password, String email){
        User user = new User();
        user.setName(username);
        new Thread(() -> mailService.sendEmail(user, password, email)).start();
        return new Response<>(true, "Mail have be sent...");
    }

    //  http://localhost:8080/
    // register/activate?emailToken=cb757e1f-8c80-4900-8a6e-65c5da1d2d14
    @GetMapping(value = "/register/activate")
    public String activateMail(@RequestParam("emailToken") String token){
        if (mailService.isActivate(token)) {
            return "/register_success.html";
        }
        return "";
    }

    @PostMapping(value = "/mail/all")
    @ResponseBody
    public Response<Boolean> sendAll(String courseName, String title, String content) {
       return mailService.sendAll(courseName, title, content);
    }


}
