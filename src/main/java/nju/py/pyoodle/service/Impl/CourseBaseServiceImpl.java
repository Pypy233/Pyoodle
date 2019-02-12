package nju.py.pyoodle.service.Impl;

import nju.py.pyoodle.dao.CourseBaseDAO;
import nju.py.pyoodle.dao.UserDAO;
import nju.py.pyoodle.domain.CourseBase;
import nju.py.pyoodle.domain.User;
import nju.py.pyoodle.enumeration.CourseState;
import nju.py.pyoodle.service.CourseBaseService;
import nju.py.pyoodle.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: py
 * @Date: 2019/2/2 上午10:56
 * @Version 1.0
 */
@Component
public class CourseBaseServiceImpl implements CourseBaseService {
    private final CourseBaseDAO courseBaseDAO;

    private final UserDAO userDAO;

    @Autowired
    public CourseBaseServiceImpl(CourseBaseDAO courseBaseDAO, UserDAO userDAO) {
        this.courseBaseDAO = courseBaseDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Response<Boolean> saveCourseBase(String name, String teacherName) {
        try {
            CourseBase courseBase = new CourseBase();
            User teacher = userDAO.getUserByName(teacherName);
            courseBase.setName(name);
            courseBase.setState(CourseState.Checking);
            courseBase.setTeacher(teacher);
            courseBaseDAO.save(courseBase);
            return new Response<>(true, "Succeed to save course base...");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to save course base...");
        }
    }
}
