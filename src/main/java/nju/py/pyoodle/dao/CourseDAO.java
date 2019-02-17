package nju.py.pyoodle.dao;

import nju.py.pyoodle.domain.Course;
import nju.py.pyoodle.enumeration.CourseState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: py
 * @Date: 2019/2/2 下午5:01
 * @Version 1.0
 */
public interface CourseDAO extends JpaRepository<Course, Integer> {
    Course getCourseByName(String name);

    List<Course> getCoursesByState(CourseState state);



}
