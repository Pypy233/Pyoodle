package nju.py.pyoodle.controller;

import nju.py.pyoodle.domain.Score;
import nju.py.pyoodle.service.ScoreService;
import nju.py.pyoodle.util.Response;
import nju.py.pyoodle.vo.ScoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: py
 * @Date: 2019/3/17 下午3:59
 * @Version 1.0
 */
@Controller
@RequestMapping("/score")
public class ScoreController {
    private final ScoreService scoreService;

    @Autowired
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @RequestMapping("/save")
    @ResponseBody
    public Response<Boolean> saveScore(@RequestParam("courseName") String courseName, @RequestParam("all") int all) {
        return scoreService.saveScore(courseName, all);
    }

    @RequestMapping("/get")
    @ResponseBody
    public Response<List<ScoreVO>> getScoresByCourse(String userName) {
        return scoreService.getScoresByCourse(userName);
    }
}
