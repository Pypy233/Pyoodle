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
public class CourseBaseController {
    private final CourseBaseService courseBaseService;


    @Autowired
    public CourseBaseController(CourseBaseService courseBaseService) {
        this.courseBaseService = courseBaseService;
    }

    @RequestMapping(value = "/courseBase/save", method = RequestMethod.POST)
    @ResponseBody
    public Response<Boolean> saveCourseBase(String name, String teacherName) {
        System.out.println(name);

        return courseBaseService.saveCourseBase(name, teacherName);
    }

    @GetMapping("/courseBase/show")
    @ResponseBody
    public Response<List<CourseBase>> listCourseBase() {
        return courseBaseService.listCourseBase();
    }



}
