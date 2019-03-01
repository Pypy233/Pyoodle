package nju.py.pyoodle.controller;

import nju.py.pyoodle.domain.CourseBase;
import nju.py.pyoodle.enumeration.CourseState;
import nju.py.pyoodle.service.CourseBaseService;
import nju.py.pyoodle.util.Response;
import nju.py.pyoodle.vo.CourseBaseVO;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/showPS")
    @ResponseBody
    public Response<List<CourseBase>> listCourseBasePassed() {
        return courseBaseService.listCourseBasePass();
    }

    @GetMapping("/showCheck")
    @ResponseBody
    public Response<List<CourseBaseVO>> listCourseCheck() {
        return courseBaseService.listCourseBaseCheck();
    }


    // Given a String[] with courseName,passed(true or false)
    @PostMapping("/check")
    @ResponseBody
    public Response<Boolean> checkCourseBase(@RequestParam("") List<String> courseBasePassMap) {
        return courseBaseService.checkCourseBase(courseBasePassMap);
    }

    
}
