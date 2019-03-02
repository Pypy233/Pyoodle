package nju.py.pyoodle.controller;

import nju.py.pyoodle.domain.Course;
import nju.py.pyoodle.service.CourseService;
import nju.py.pyoodle.util.DateUtil;
import nju.py.pyoodle.util.Response;
import nju.py.pyoodle.vo.CourseVO;
import nju.py.pyoodle.vo.JoinableCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Response<Boolean> saveCourse(String username, String time, int classNum, int limit, String courseBaseName) {
        return courseService.saveCourse(username, DateUtil.parse(time), classNum, limit, courseBaseName);
    }

    @GetMapping("/course/passed")
    @ResponseBody
    public Response<List<Course>> listCourse() {
        return courseService.listPassedCourse();
    }

    @PostMapping("/course/join")
    @ResponseBody
    public Response<Boolean> joinCourse(String courseName, String userName) {
        return courseService.joinCourse(courseName, userName);
    }

    // Given a String[] with courseName,username,passed(true or false)
    @PostMapping("/course/check")
    @ResponseBody
    public Response<Boolean> checkCourse(@RequestParam("courseMap") List<String> coursePassMap) {
        return courseService.checkCourse(coursePassMap);
    }


    @GetMapping("/course/listJoin")
    @ResponseBody
    public Response<List<JoinableCourse>> listJoinableCourse(String userName) {
        return courseService.listJoinableCourse(userName);
    }

    @GetMapping("/course/listChecking")
    @ResponseBody
    public Response<List<CourseVO>> listCourseToBeChecked() {
        return courseService.listToBeCheckedCourse();
    }

}
