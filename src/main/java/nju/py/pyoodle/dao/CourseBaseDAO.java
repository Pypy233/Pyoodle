package nju.py.pyoodle.dao;

import nju.py.pyoodle.domain.CourseBase;
import nju.py.pyoodle.enumeration.CourseState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: py
 * @Date: 2019/2/2 上午10:33
 * @Version 1.0
 */
public interface CourseBaseDAO extends JpaRepository<CourseBase, Integer> {
    CourseBase getCourseBaseByName(String name);

    List<CourseBase> getCourseBasesByState(CourseState state);

}
