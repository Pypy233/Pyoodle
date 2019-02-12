package nju.py.pyoodle.service.Impl;

import nju.py.pyoodle.dao.CourseBaseDAO;
import nju.py.pyoodle.dao.CourseDAO;
import nju.py.pyoodle.domain.Course;
import nju.py.pyoodle.domain.CourseBase;
import nju.py.pyoodle.enumeration.CourseState;
import nju.py.pyoodle.service.AdminService;
import nju.py.pyoodle.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: py
 * @Date: 2019/2/2 下午5:00
 * @Version 1.0
 */
@Component
public class AdminServiceImpl implements AdminService {
    private final CourseBaseDAO courseBaseDAO;
    private final CourseDAO courseDAO;

    @Autowired
    public AdminServiceImpl(CourseBaseDAO courseBaseDAO, CourseDAO courseDAO) {
        this.courseBaseDAO = courseBaseDAO;
        this.courseDAO = courseDAO;
    }

    @Override
    public Response checkCourseBase(String courseBaseName, boolean accept) {
        CourseBase courseBase = courseBaseDAO.getCourseBaseByName(courseBaseName);
        if (accept) {
            courseBase.setState(CourseState.Success);
        } else {
            courseBase.setState(CourseState.Fail);
        }
        courseBaseDAO.save(courseBase);
        return new Response();
    }

    @Override
    public Response checkCourse(String courseName, boolean accept) {
        Course course = courseDAO.getCourseByName(courseName);
        if (accept) {
            course.setState(CourseState.Success);
        } else {
            course.setState(CourseState.Fail);
        }
        courseDAO.save(course);
        return new Response();
    }
}
