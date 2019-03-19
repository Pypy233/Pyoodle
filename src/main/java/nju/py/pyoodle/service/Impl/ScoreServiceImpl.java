package nju.py.pyoodle.service.Impl;

import nju.py.pyoodle.dao.CourseDAO;
import nju.py.pyoodle.dao.ScoreDAO;
import nju.py.pyoodle.dao.UserDAO;
import nju.py.pyoodle.domain.Course;
import nju.py.pyoodle.domain.Score;
import nju.py.pyoodle.domain.User;
import nju.py.pyoodle.enumeration.UserType;
import nju.py.pyoodle.service.ScoreService;
import nju.py.pyoodle.util.ExcelUtil;
import nju.py.pyoodle.util.Response;
import nju.py.pyoodle.vo.ScoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public Response<Boolean> saveScore(String courseName, int all) {
        try {
            Course course = courseDAO.getCourseByName(courseName);
            List<Map<String, Integer>> mapList = ExcelUtil.readExcel();
            for (Map<String, Integer> map: mapList) {
                    String studentNum = map.get("学号") + "";
                    User user = userDAO.getUserByStudentNumber(studentNum);
                    int point = Integer.parseInt(map.get("分数") + "");
                    Score score = new Score();
                    score.setStudent(user);
                    score.setCourse(course);
                    score.setVal(point);
                    score.setAllOrAlone(all);
                    scoreDAO.save(score);

                    System.out.println(map.get("学号") + ": " + map.get("分数"));
            }
            return new Response<>(true, "Succeed...");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail...");
        }
    }

    @Override
    public Response<List<ScoreVO>> getScoresByCourse(String userName) {
        try {
            User user = userDAO.getUserByName(userName);
            List<ScoreVO> scoreVOList = new ArrayList<>();
            if (user.getType().equals(UserType.TEACHER)) {
                List<Course> courseList = courseDAO.getCoursesByTeacher(user);
                for(Course course: courseList) {
                    List <Score> scoreList1 = scoreDAO.getScoresByCourse(course);
                    for(Score score: scoreList1) {
                        scoreVOList.add(new ScoreVO(score));
                    }
                }

            } else {
                List<Course> courseList = courseDAO.findAll();
                for(Course course: courseList) {
                    if(course.getStudents().contains(user)) {
                        List<Score> scoreList1 = scoreDAO.getScoresByCourse(course);
                        for(Score score: scoreList1) {
                            if(score.getStudent().getName().equals(userName) || score.getAllOrAlone() == 1) {
                                scoreVOList.add(new ScoreVO(score));
                            }
                        }
                    }
                }
            }
            return new Response<>(true, scoreVOList);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to list score by course...");
        }
    }
}
