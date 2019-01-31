package nju.py.pyoodle.controller;

import nju.py.pyoodle.dao.UserDAO;
import nju.py.pyoodle.domain.User;
import nju.py.pyoodle.service.MailService;
import nju.py.pyoodle.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: py
 * @Date: 2019/1/31 下午11:12
 * @Version 1.0
 */
@Controller
@RequestMapping("/register")
public class EmailController {
    @Autowired
    private MailService mailService;
    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "/mail")
    @ResponseBody
    public Response<Boolean> sendEmail(String username, String email){
        User user = userDAO.getUserByName(username);
        new Thread(() -> mailService.sendEmail(user, email)).start();
        return new Response<>(true, "Mail have be sent...");
    }

    @RequestMapping(value = "/activate")
    public Response<Boolean> activateMail(String username, @RequestParam String token){
        if (mailService.isActivate(username, token)) {
            return new Response<>(true, "Succeed to activate...");
        }
        return new Response<>(false, "Fail to activate...");
    }
}
