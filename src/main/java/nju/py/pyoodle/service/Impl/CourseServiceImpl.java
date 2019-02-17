package nju.py.pyoodle.service.Impl;

import nju.py.pyoodle.dao.CourseDAO;
import nju.py.pyoodle.dao.UserDAO;
import nju.py.pyoodle.domain.Course;
import nju.py.pyoodle.enumeration.CourseState;
import nju.py.pyoodle.service.CourseService;
import nju.py.pyoodle.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: py
 * @Date: 2019/2/14 下午10:38
 * @Version 1.0
 */
@Component
public class CourseServiceImpl implements CourseService {
    private final CourseDAO courseDAO;
    private final UserDAO userDAO;

    @Autowired
    public CourseServiceImpl(CourseDAO courseDAO, UserDAO userDAO) {
        this.courseDAO = courseDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Response<Boolean> saveCourse(String username, LocalDate time, int classNum, int limit) {
        try {
            Course course = new Course();
            course.setTeacher(userDAO.getUserByName(username));
            course.setState(CourseState.Checking);
            course.setTime(time);
            course.setLimit(limit);
            course.setClassNum(classNum);
            courseDAO.save(course);
            return new Response<>(true, "Succeed to save course...");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to save course...");
        }
    }

    @Override
    public Response<List<Course>> listPassedCourse() {
        try {
            return new Response<>(true, courseDAO.getCoursesByState(CourseState.Success));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to list passed course...");
        }
    }
}
