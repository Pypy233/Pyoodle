package nju.py.pyoodle.service.Impl;

import nju.py.pyoodle.dao.CourseDAO;
import nju.py.pyoodle.dao.ScoreDAO;
import nju.py.pyoodle.dao.UserDAO;
import nju.py.pyoodle.domain.Course;
import nju.py.pyoodle.domain.Score;
import nju.py.pyoodle.domain.User;
import nju.py.pyoodle.service.ScoreService;
import nju.py.pyoodle.util.Response;
import nju.py.pyoodle.vo.ScoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: py
 * @Date: 2019/3/17 下午4:00
 * @Version 1.0
 */
@Component
public class ScoreServiceImpl implements ScoreService {
    private final CourseDAO courseDAO;
    private final ScoreDAO scoreDAO;

    private final UserDAO userDAO;

    @Autowired
    public ScoreServiceImpl(CourseDAO courseDAO, ScoreDAO scoreDAO, UserDAO userDAO) {
        this.courseDAO = courseDAO;
        this.scoreDAO = scoreDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Response<Boolean> saveScore(List<String> nameScoreList) {
        try {
            String courseName = nameScoreList.get(0);
            Course course = courseDAO.getCourseByName(courseName);
            for(int i = 1; i < nameScoreList.size(); i++) {
                String nameScore = nameScoreList.get(i);
                String name = nameScore.split(" ")[0];
                int score = Integer.parseInt(nameScore.split(" ")[1]);
                Score score1 = new Score();
                score1.setVal(score);
                score1.setStudent(userDAO.getUserByName(name));
                score1.setCourse(course);
                scoreDAO.save(score1);
            }
            return new Response<>(true, "Succeed...");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail...");
        }
    }

    @Override
    public Response<List<ScoreVO>> getScoresByCourse(String courseName) {
        try {
            Course course = courseDAO.getCourseByName(courseName);
            List<User> userList = course.getStudents();
            List<Score> scoreList = scoreDAO.getScoresByCourse(course);
            List<ScoreVO> resList = new ArrayList<>();
            if (scoreList == null || scoreList.size() < userList.size()) {
                if ( scoreList == null || scoreList.size() == 0 ) {
                    for (User user : userList) {
                        Score score = new Score();
                        score.setCourse(course);
                        score.setStudent(user);
                        score.setVal(0);
                        scoreDAO.save(score);
                        resList.add(new ScoreVO(score));
                    }
                }
            }
                else {
                    for (User user: userList) {
                        for(Score score: scoreList) {
                            if (score.getStudent().getName().equals(user.getName())) {
                                resList.add(new ScoreVO(score));
                                break;
                            }

                            Score score1 = new Score();
                            score.setCourse(course);
                            score.setStudent(user);
                            score.setVal(0);
                            scoreDAO.save(score1);
                            resList.add(new ScoreVO(score1));
                        }
                    }
                }
            return new Response<>(true, resList);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to list score by course...");
        }
    }
}
