package nju.py.pyoodle.service.Impl;

import nju.py.pyoodle.dao.CourseBaseDAO;
import nju.py.pyoodle.dao.UserDAO;
import nju.py.pyoodle.domain.Course;
import nju.py.pyoodle.domain.CourseBase;
import nju.py.pyoodle.domain.User;
import nju.py.pyoodle.enumeration.CourseState;
import nju.py.pyoodle.service.CourseBaseService;
import nju.py.pyoodle.util.Response;
import nju.py.pyoodle.vo.CourseBaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Response<List<CourseBase>> listCourseBasePass() {
        try {
            return new Response<>(true, courseBaseDAO.getCourseBasesByState(CourseState.Success));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to list");
        }
    }

    @Override
    public Response<List<CourseBaseVO>> listCourseBaseCheck() {
        try {
            List<CourseBase> courseBaseList = courseBaseDAO.getCourseBasesByState(CourseState.Checking);
            List<CourseBaseVO> courseBaseVOList = new ArrayList<>();
            for (CourseBase courseBase: courseBaseList) {
                courseBaseVOList.add(new CourseBaseVO(courseBase));
            }
            return new Response<>(true, courseBaseVOList);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to list course...");
        }
    }

    @Override
    public Response<Boolean> checkCourseBase(List<String> courseBasePassMap) {
        try {
            for (String pair : courseBasePassMap) {
                String[] arr = pair.split("_");
                String courseBaseName = arr[0];

                CourseBase course = courseBaseDAO.getCourseBaseByName(courseBaseName);
                if ( arr[1].equals("1") ) {
                    course.setState(CourseState.Success);
                } else {
                    course.setState(CourseState.Fail);
                }

                courseBaseDAO.save(course);
            }
            return new Response<>(true, "Succeed to check course base list...");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to check course base list...");
        }
    }
}
