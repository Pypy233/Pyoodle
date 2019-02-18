package nju.py.pyoodle.service;

import nju.py.pyoodle.domain.Course;
import nju.py.pyoodle.util.Response;
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
    Response<Boolean> saveCourse(String username, LocalDate time, int classNum, int limit);

    Response<List<Course>> listPassedCourse();


}