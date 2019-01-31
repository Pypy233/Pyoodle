package nju.py.pyoodle.controller;

import nju.py.pyoodle.service.UserService;
import nju.py.pyoodle.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: py
 * @Date: 2019/1/30 上午2:29
 * @Version 1.0
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String getIndex() {
        return "/index.html";
    }

    @GetMapping("/login")
    @ResponseBody
    public Response<Boolean> canLogin(String username, String password) {
        return userService.canLogin(username, password);
    }




}
