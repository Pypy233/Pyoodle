package nju.py.pyoodle.dao;

import nju.py.pyoodle.domain.Course;
import nju.py.pyoodle.domain.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * @Author: py
 * @Date: 2019/3/2 上午11:22
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CourseDAOTest {
    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private UserDAO userDAO;

    @Test
    public void getCourseByNameAndTeacherAndTime() {
        User user = userDAO.getUserByName("whr");
        LocalDate date = LocalDate.parse("2019-02-26");
        String name = "2019-02-27 1";
        Course course = courseDAO.getCourseByNameAndTeacherAndTime(name, user, date);
        Course cmpCourse = courseDAO.getCourseByName(name);
        assert cmpCourse.getName().equals(name);
        assert cmpCourse.getTime().equals(date);
        assert cmpCourse.getTeacher().equals(user);
        assert course != null;
    }
}