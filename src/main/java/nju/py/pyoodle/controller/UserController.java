package nju.py.pyoodle.controller;

import nju.py.pyoodle.domain.User;
import nju.py.pyoodle.enumeration.UserType;
import nju.py.pyoodle.service.MailService;
import nju.py.pyoodle.service.UserService;
import nju.py.pyoodle.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: py
 * @Date: 2019/1/30 上午2:29
 * @Version 1.0
 */
@Controller
public class UserController {

    private final UserService userService;

    private final MailService mailService;

    @Autowired
    public UserController(UserService userService, MailService mailService) {
        this.userService = userService;
        this.mailService = mailService;
    }


    @RequestMapping("/")
    public String getIndex() {
        return "/index.html";
    }

    @GetMapping("/login")
    @ResponseBody
    public Response<UserType> canLogin(String username, String password) {
        return userService.canLogin(username, password);
    }

    @PostMapping("/register")
    @ResponseBody
    public Response<Boolean> register(String username, String password, String email) {
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        user.setEmail(email);
        userService.saveUser(user);
        return new Response<>(true, "Succeed to register");

    }

    @PostMapping("/update0")
    @ResponseBody
    public Response<Boolean> updateUser(String ordinaryUserName, String newUserName, String password, String email, String studentNumber) {
        return userService.updateUser(ordinaryUserName, newUserName, password, email, studentNumber);
    }

    @PostMapping("/update")
    @ResponseBody
    public Response<Boolean> updateUser(String ordinaryName, String newUserName, String studentNumber) {
        return userService.updateUser(ordinaryName, newUserName, studentNumber);
    }

    @GetMapping("/user")
    @ResponseBody
    public Response<User> getNum(String userName) {
        return userService.getStudentNum(userName);
    }

    @GetMapping("/teaNum")
    @ResponseBody
    public Response<Integer> getTeacherNum() {
        return userService.getNum(UserType.TEACHER);
    }

    @GetMapping("/stuNum")
    @ResponseBody
    public Response<Integer> getStuNum() {
        return userService.getNum(UserType.FRESH);
    }




}
