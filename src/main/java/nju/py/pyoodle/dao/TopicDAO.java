package nju.py.pyoodle.dao;

import nju.py.pyoodle.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: py
 * @Date: 2019/3/10 下午12:16
 * @Version 1.0
 */
public interface TopicDAO extends JpaRepository<Topic, Integer> {
    Topic getTopicByTitle(String title);
}
