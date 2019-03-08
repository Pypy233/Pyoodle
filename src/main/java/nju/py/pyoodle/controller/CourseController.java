package nju.py.pyoodle.controller;

import nju.py.pyoodle.domain.Course;
import nju.py.pyoodle.service.CourseService;
import nju.py.pyoodle.util.DateUtil;
import nju.py.pyoodle.util.Response;
import nju.py.pyoodle.vo.CourseVO;
import nju.py.pyoodle.vo.FileItemVO;
import nju.py.pyoodle.vo.JoinableCourse;
import nju.py.pyoodle.vo.JoinedCourseVO;
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
    public Response<List<CourseVO>> listCourse() {
        return courseService.listPassedCourse();
    }

    @PostMapping("/course/join")
    @ResponseBody
    public Response<Boolean> joinCourse(@RequestParam("courseNameList") List<String> courseName) {
        return courseService.joinCourse(courseName);
    }

    // Given a String[] with courseName,username,passed(true or false)
    @PostMapping("/course/check")
    @ResponseBody
    public Response<Boolean> checkCourse(@RequestParam("courseMap") List<String> coursePassMap) {
        return courseService.checkCourse(coursePassMap);
    }

    @GetMapping("/course/listAll")
    @ResponseBody
    public Response<List<CourseVO>> listAll() {
        return courseService.listAll();
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


    @GetMapping("/course/listPPT")
    @ResponseBody
    public Response<List<FileItemVO>> listPPT(String courseName) {
        return courseService.listPPT(courseName);
    }

    @GetMapping("/course/listHw")
    @ResponseBody
    public Response<List<FileItemVO>> listHw(String courseName) {
        return courseService.listHw(courseName);
    }

    @GetMapping("/course/listJoined")
    @ResponseBody
    public Response<List<JoinedCourseVO>> listJoinedCourse(String userName) {
        return courseService.listJoinedCourse(userName);
    }

    @PostMapping("/course/drop")
    @ResponseBody
    public Response<Boolean> dropCourse(@RequestParam("courseNameList") List<String> idCourseList) {
        return courseService.dropCourse(idCourseList);
    }

}
