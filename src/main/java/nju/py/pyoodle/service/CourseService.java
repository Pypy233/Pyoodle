package nju.py.pyoodle.service;

import nju.py.pyoodle.domain.Course;
import nju.py.pyoodle.util.Response;
import nju.py.pyoodle.vo.CourseVO;
import nju.py.pyoodle.vo.FileItemVO;
import nju.py.pyoodle.vo.JoinableCourse;
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

    Response<List<Course>> listPassedCourse();

    Response<Boolean> joinCourse(String courseName, String userName);

    Response<List<JoinableCourse>> listJoinableCourse(String userName);

    Response<Boolean> checkCourse(List<String> coursePassMap);

    Response<List<CourseVO>> listToBeCheckedCourse();

    Response<List<CourseVO>> listAll();

    Response<List<FileItemVO>> listPPT(String courseName);

    Response<List<FileItemVO>> listHw(String courseName);


}
