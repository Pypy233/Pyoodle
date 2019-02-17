package nju.py.pyoodle.dao;

import nju.py.pyoodle.domain.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: py
 * @Date: 2019/2/16 下午2:49
 * @Version 1.0
 */
public interface HomeworkDAO extends JpaRepository<Homework, Integer> {
}
