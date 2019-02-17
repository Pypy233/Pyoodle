package nju.py.pyoodle.controller;

import nju.py.pyoodle.domain.Course;
import nju.py.pyoodle.service.CourseService;
import nju.py.pyoodle.util.DateUtil;
import nju.py.pyoodle.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: py
 * @Date: 2019/2/14 下午10:41
 * @Version 1.0
 */
@RestController
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/course/save")
    @ResponseBody
    public Response<Boolean> saveCourse(String username, String time, int classNum, int limit) {
        return courseService.saveCourse(username, DateUtil.parse(time), classNum, limit);
    }

    @GetMapping("/course/passed")
    @ResponseBody
    public Response<List<Course>> listCourse() {
        return courseService.listPassedCourse();
    }

}
