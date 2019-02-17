package nju.py.pyoodle.controller;

import nju.py.pyoodle.domain.CourseBase;
import nju.py.pyoodle.service.CourseBaseService;
import nju.py.pyoodle.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: py
 * @Date: 2019/2/2 下午12:03
 * @Version 1.0
 */
@RestController
@RequestMapping("/courseBase")
public class CourseBaseController {
    private final CourseBaseService courseBaseService;


    @Autowired
    public CourseBaseController(CourseBaseService courseBaseService) {
        this.courseBaseService = courseBaseService;
    }

    @PostMapping(value = "/save")
    @ResponseBody
    public Response<Boolean> saveCourseBase(String courseName, String user) {
        return courseBaseService.saveCourseBase(courseName, user);
    }

    @GetMapping("/show")
    @ResponseBody
    public Response<List<CourseBase>> listCourseBase() {
        return courseBaseService.listCourseBase();
    }



}
