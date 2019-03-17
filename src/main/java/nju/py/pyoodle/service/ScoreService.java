package nju.py.pyoodle.service;

import nju.py.pyoodle.domain.Course;
import nju.py.pyoodle.domain.Score;
import nju.py.pyoodle.util.Response;
import nju.py.pyoodle.vo.ScoreVO;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Author: py
 * @Date: 2019/3/17 下午1:15
 * @Version 1.0
 */
@Service
public interface ScoreService {
    Response<Boolean> saveScore(List<String> nameScoreList);

    Response<List<ScoreVO>> getScoresByCourse(String courseName);
}
