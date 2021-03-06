package nju.py.pyoodle.dao;

import nju.py.pyoodle.domain.Course;
import nju.py.pyoodle.domain.User;
import nju.py.pyoodle.enumeration.CourseState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: py
 * @Date: 2019/2/2 下午5:01
 * @Version 1.0
 */
public interface CourseDAO extends JpaRepository<Course, Integer> {
    Course getCourseByName(String name);

    List<Course> getCoursesByState(CourseState state);

    Course getCourseByNameAndTeacher(String courseName, User teacher);

    List<Course> getCoursesByTeacher(User user);

    Course getCourseByNameAndTeacherAndTime(String courseName, User teacher, LocalDate time);
}
