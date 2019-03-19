package nju.py.pyoodle.service.Impl;

import nju.py.pyoodle.dao.*;
import nju.py.pyoodle.domain.*;
import nju.py.pyoodle.service.BBSService;
import nju.py.pyoodle.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: py
 * @Date: 2019/3/18 上午3:47
 * @Version 1.0
 */
@Component
public class BBSServiceImpl implements BBSService {
    private final BBSDAO bbsDAO;
    private final CourseDAO courseDAO;

    private final UserDAO userDAO;

    private final TopicDAO topicDAO;

    private final CommentDAO commentDAO;

    @Autowired
    public BBSServiceImpl(BBSDAO bbsDAO, CourseDAO courseDAO, UserDAO userDAO, TopicDAO topicDAO, CommentDAO commentDAO) {
        this.bbsDAO = bbsDAO;
        this.courseDAO = courseDAO;
        this.userDAO = userDAO;
        this.topicDAO = topicDAO;
        this.commentDAO = commentDAO;
    }

    @Override
    public Response<List<Topic>> listTopic(String courseName) {
        try {
            Course course = courseDAO.getCourseByName(courseName);
            BBS bbs = bbsDAO.getBBSByCourse(course);
            List<Topic> topicList;
            topicList = bbs.getTopicList();
            return new Response<>(true, topicList);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to list topics");
        }
    }

    @Override
    public Response<Boolean> saveTopic(String courseName, String topicName, String userName) {
        try {
            Course course = courseDAO.getCourseByName(courseName);
            BBS bbs = bbsDAO.getBBSByCourse(course);
            List<Topic> topicList = bbs.getTopicList();
            Topic topic = new Topic();
            topic.setTitle(topicName);
            User user = userDAO.getUserByName(userName);
            topic.setUser(user);
            topicDAO.save(topic);
            topicList.add(topic);
            bbs.setTopicList(topicList);
            bbsDAO.save(bbs);
            return new Response<>(true, "Succeed to save topic");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to list topics");
        }
    }

    @Override
    public Response<List<Comment>> listComment(String topicName) {
        try {
            Topic topic = topicDAO.getTopicByTitle(topicName);
            return new Response<>(true, topic.getCommentList());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to list comment");
        }
    }

    @Override
    public Response<Boolean> addComment(String topicName, String userName, String content, String explanation) {
        try {
            Topic topic = topicDAO.getTopicByTitle(topicName);
            User user = userDAO.getUserByName(userName);
            Comment comment = new Comment();
            comment.setContent(content);
            comment.setExplanation(explanation);
            comment.setUser(user);
            commentDAO.save(comment);
            List<Comment> commentList = topic.getCommentList();
            commentList.add(comment);
            topicDAO.save(topic);
            return new Response<>(true, "Succeed to add comments...");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to add comments...");
        }
    }
}
