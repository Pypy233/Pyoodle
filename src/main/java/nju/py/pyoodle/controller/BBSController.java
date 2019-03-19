package nju.py.pyoodle.controller;

import com.sun.org.apache.regexp.internal.RE;
import nju.py.pyoodle.domain.Comment;
import nju.py.pyoodle.domain.Topic;
import nju.py.pyoodle.service.BBSService;
import nju.py.pyoodle.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: py
 * @Date: 2019/3/18 上午3:43
 * @Version 1.0
 */
@Controller
@RequestMapping("/bbs")
public class BBSController {
   private final BBSService bbsService;

    @Autowired
    public BBSController(BBSService bbsService) {
        this.bbsService = bbsService;
    }

    @RequestMapping("/ls")
    @ResponseBody
    public Response<List<Topic>> listTopic(String courseName) {
        return bbsService.listTopic(courseName);
    }

    @RequestMapping("/topic")
    @ResponseBody
    public Response<Boolean> saveTopic(String courseName, String topicName, String userName) {
        return bbsService.saveTopic(courseName, topicName, userName);
    }

    @RequestMapping("/detail")
    @ResponseBody
    public Response<List<Comment>> listComment(String topicName) {
        return bbsService.listComment(topicName);
    }

    @PostMapping("/comment")
    @ResponseBody
    public Response<Boolean> addComment(String topicName, String userName, String content, String explanation) {
        return bbsService.addComment(topicName, userName, content, explanation);
    }
}
