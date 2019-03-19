package nju.py.pyoodle.dao;

import nju.py.pyoodle.domain.DroppedCourse;
import nju.py.pyoodle.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: py
 * @Date: 2019/3/18 上午2:34
 * @Version 1.0
 */
public interface DroppedCourseDAO extends JpaRepository<DroppedCourse, Integer> {
    List<DroppedCourse> getDroppedCoursesByUser(User user);
}
