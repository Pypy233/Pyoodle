package nju.py.pyoodle.service;

import nju.py.pyoodle.domain.BBS;
import nju.py.pyoodle.domain.Comment;
import nju.py.pyoodle.domain.Topic;
import nju.py.pyoodle.util.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: py
 * @Date: 2019/3/18 上午3:45
 * @Version 1.0
 */
@Service
public interface BBSService {
    Response<List<Topic>> listTopic(String courseName);

    Response<Boolean> saveTopic(String courseName, String topicName, String userName);

    Response<List<Comment>> listComment(String topicName);

    Response<Boolean> addComment(String topicName, String userName, String content, String explanation);
}
