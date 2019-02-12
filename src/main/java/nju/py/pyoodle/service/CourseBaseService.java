package nju.py.pyoodle.service;

import nju.py.pyoodle.domain.User;
import nju.py.pyoodle.util.Response;
import org.springframework.stereotype.Service;

/**
 * @Author: py
 * @Date: 2019/2/2 上午10:35
 * @Version 1.0
 */
@Service
public interface CourseBaseService {
    Response<Boolean> saveCourseBase(String name, String teacherName);
}
