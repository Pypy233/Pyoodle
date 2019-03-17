package nju.py.pyoodle.service;

import nju.py.pyoodle.domain.Course;
import nju.py.pyoodle.domain.Score;
import nju.py.pyoodle.util.Response;
import nju.py.pyoodle.vo.CourseVO;
import nju.py.pyoodle.vo.FileItemVO;
import nju.py.pyoodle.vo.JoinableCourse;
import nju.py.pyoodle.vo.JoinedCourseVO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: py
 * @Date: 2019/2/14 下午10:37
 * @Version 1.0
 */
@Service
public interface CourseService {
    Response<Boolean> saveCourse(String username, LocalDate time, int classNum, int limit, String courseBaseName);

    Response<List<CourseVO>> listPassedCourse();

    Response<Boolean> joinCourse(List<String> courseNameList);

    Response<List<JoinableCourse>> listJoinableCourse(String userName);

    Response<Boolean> checkCourse(List<String> coursePassMap);

    Response<List<CourseVO>> listToBeCheckedCourse();

    Response<List<CourseVO>> listAll();

    Response<List<FileItemVO>> listPPT(String courseName);

    Response<List<FileItemVO>> listHw(String courseName);

    Response<Boolean> dropCourse(List<String> idCourseList);

    Response<List<JoinedCourseVO>> listJoinedCourse(String userName);

    Response<List<String>> listCourseByTeacher(String userName);

    Response<List<JoinedCourseVO>> listCourse(String userName);




}
