package nju.py.pyoodle.controller;

import nju.py.pyoodle.dao.UserDAO;
import nju.py.pyoodle.domain.User;
import nju.py.pyoodle.service.MailService;
import nju.py.pyoodle.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: py
 * @Date: 2019/1/31 下午11:12
 * @Version 1.0
 */
@Controller
@RequestMapping("/register")
public class EmailController {
    private final MailService mailService;
    private final UserDAO userDAO;

    @Autowired
    public EmailController(MailService mailService, UserDAO userDAO) {
        this.mailService = mailService;
        this.userDAO = userDAO;
    }

    @PostMapping(value = "/mail")
    @ResponseBody
    public Response<Boolean> sendEmail(String username, String password, String email){
        User user = new User();
        user.setName(username);
        new Thread(() -> mailService.sendEmail(user, password, email)).start();
        return new Response<>(true, "Mail have be sent...");
    }

    //  http://localhost:8080/
    // register/activate?emailToken=cb757e1f-8c80-4900-8a6e-65c5da1d2d14
    @GetMapping(value = "/activate")
    public String activateMail(@RequestParam("emailToken") String token){
        System.out.println("***********");
        System.out.println(token);
        if (mailService.isActivate(token)) {
            return "/register_success.html";
        }
        return "";
    }
}
