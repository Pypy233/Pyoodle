package nju.py.pyoodle.service;

import nju.py.pyoodle.domain.CourseBase;
import nju.py.pyoodle.util.Response;
import nju.py.pyoodle.vo.CourseBaseVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: py
 * @Date: 2019/2/2 上午10:35
 * @Version 1.0
 */
@Service
public interface CourseBaseService {
    Response<Boolean> saveCourseBase(String name, String teacherName);

    Response<List<CourseBase>> listCourseBasePass();

    Response<List<CourseBaseVO>> listCourseBaseCheck();

    Response<Boolean> checkCourseBase(List<String> courseBasePassMap);
}
