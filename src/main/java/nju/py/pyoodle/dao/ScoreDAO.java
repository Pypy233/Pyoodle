package nju.py.pyoodle.dao;

import nju.py.pyoodle.domain.Course;
import nju.py.pyoodle.domain.Score;
import nju.py.pyoodle.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: py
 * @Date: 2019/3/17 下午1:13
 * @Version 1.0
 */
public interface ScoreDAO extends JpaRepository<Score, Integer> {
    List<Score> getScoresByCourse(Course course);
}
