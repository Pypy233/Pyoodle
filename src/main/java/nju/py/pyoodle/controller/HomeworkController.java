package nju.py.pyoodle.controller;

import nju.py.pyoodle.service.HomeworkService;
import nju.py.pyoodle.util.DateUtil;
import nju.py.pyoodle.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: py
 * @Date: 2019/2/16 下午3:03
 * @Version 1.0
 */
@RestController
@RequestMapping("/hw")
public class HomeworkController {
    private final HomeworkService homeworkService;

    @Autowired
    public HomeworkController(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }


    @RequestMapping("/save")
    @ResponseBody
    public Response<Boolean> addHw(String name, String courseName, int size, String type, String ddl, String description) {
        return homeworkService.addHw(name, courseName, size, type, ddl, description);
    }


}
