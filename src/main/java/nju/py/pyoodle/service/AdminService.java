package nju.py.pyoodle.service;

import nju.py.pyoodle.util.Response;
import org.springframework.stereotype.Service;

/**
 * @Author: py
 * @Date: 2019/2/2 下午4:58
 * @Version 1.0
 */
@Service
public interface AdminService {
    Response checkCourseBase(String courseBaseName, boolean accept);

    Response checkCourse(String courseName, boolean accept);


}
