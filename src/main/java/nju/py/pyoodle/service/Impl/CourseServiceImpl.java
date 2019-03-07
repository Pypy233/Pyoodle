package nju.py.pyoodle.service.Impl;

import nju.py.pyoodle.dao.CourseBaseDAO;
import nju.py.pyoodle.dao.CourseDAO;
import nju.py.pyoodle.dao.UserDAO;
import nju.py.pyoodle.domain.Course;
import nju.py.pyoodle.domain.CourseBase;
import nju.py.pyoodle.domain.User;
import nju.py.pyoodle.enumeration.CourseState;
import nju.py.pyoodle.service.CourseService;
import nju.py.pyoodle.util.FileUtil;
import nju.py.pyoodle.util.Response;
import nju.py.pyoodle.vo.CourseVO;
import nju.py.pyoodle.vo.FileItemVO;
import nju.py.pyoodle.vo.JoinableCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: py
 * @Date: 2019/2/14 下午10:38
 * @Version 1.0
 */
@Component
public class CourseServiceImpl implements CourseService {
    private final CourseDAO courseDAO;
    private final UserDAO userDAO;
    private final CourseBaseDAO courseBaseDAO;

    @Autowired
    public CourseServiceImpl(CourseDAO courseDAO, UserDAO userDAO, CourseBaseDAO courseBaseDAO) {
        this.courseDAO = courseDAO;
        this.userDAO = userDAO;
        this.courseBaseDAO = courseBaseDAO;
    }

    @Override
    public Response<Boolean> saveCourse(String username, LocalDate time, int classNum, int limit, String courseBaseName) {
        try {
            Course course = new Course();
            course.setTeacher(userDAO.getUserByName(username));
            course.setState(CourseState.Checking);
            course.setName(time + " " + courseBaseName);
            course.setCourseBase(courseBaseDAO.getCourseBaseByName(courseBaseName));
            course.setTime(time);
            course.setLimit(limit);
            course.setClassNum(classNum);
            courseDAO.save(course);
            return new Response<>(true, "Succeed to save course...");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to save course...");
        }
    }

    @Override
    public Response<List<Course>> listPassedCourse() {
        try {
            return new Response<>(true, courseDAO.getCoursesByState(CourseState.Success));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to list passed course...");
        }
    }

    @Override
    public Response<Boolean> joinCourse(String courseName, String userName) {
        try {
            Course course = courseDAO.getCourseByName(courseName);
            List<User> studentList = course.getStudents();

            User user = userDAO.getUserByName(userName);
            studentList.add(user);
            course.setStudents(studentList);
            courseDAO.save(course);
            return new Response<>(true, "Succeed to join course...");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to join course...");
        }
    }

    @Override
    public Response<List<JoinableCourse>> listJoinableCourse(String userName) {
       try {
           List<Course> courseList = courseDAO.getCoursesByState(CourseState.Success);
           List<JoinableCourse> resList = new ArrayList<>();

           User user = userDAO.getUserByName(userName);

           for (Course course: courseList) {
               if (!course.getStudents().contains(user)) {
                   resList.add(new JoinableCourse(course));
               }
           }
           return new Response<>(true, resList);

       } catch (Exception ex) {
           ex.printStackTrace();
           return new Response<>(false, "Fail to list joinable course...");

       }    }

    @Override
    public Response<Boolean> checkCourse(List<String> coursePassMap) {
        try {
            for (String pair : coursePassMap) {
                String[] arr = pair.split("_");
                String courseName = arr[0];

                Course course = courseDAO.getCourseByName(courseName);
                CourseBase courseBase = course.getCourseBase();
                String courseBaseName = courseBase.getName();
                if ( arr[3].equals("1")) {
                    course.setState(CourseState.Success);
                    FileUtil.copyBase(courseName, courseBaseName);
                }else {

                    course.setState(CourseState.Fail);
                }

                courseDAO.save(course);
            }
            return new Response<>(true, "Succeed to check course list...");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to check course list...");
        }
    }

    @Override
    public Response<List<CourseVO>> listToBeCheckedCourse() {
        try {
            List<Course> courseList = courseDAO.getCoursesByState(CourseState.Checking);
            List<CourseVO> voList = new ArrayList<>();
            for (Course course: courseList) {
                voList.add(new CourseVO(course));
            }

            return new Response<>(true, voList);
        } catch (Exception ex) {
            return new Response<>(false, "Fail to list course to be checked...");
        }
    }

    @Override
    public Response<List<CourseVO>> listAll() {
        try {
            List<CourseVO> courseVOList = new ArrayList<>();
            List<Course> courseList = courseDAO.getCoursesByState(CourseState.Success);
            for (Course course: courseList) {
                courseVOList.add(new CourseVO(course));
            }
            return new Response<>(true, courseVOList);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to list all course...");
        }
    }

    @Override
    public Response<List<FileItemVO>> listPPT(String courseName) {
        try {
            List<String> pptPathList = FileUtil.listPPTName(courseName);
            List<FileItemVO> resList = new ArrayList<>();
            for (String s: pptPathList) {
                FileItemVO vo = new FileItemVO();
                vo.setPath(s);
                String[] pathArr = s.split("/");
                vo.setName(pathArr[pathArr.length - 1]);
                resList.add(vo);
            }
            return new Response<>(true, resList);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to list ppt");
        }
    }

    @Override
    public Response<List<FileItemVO>> listHw(String courseName) {
        try {
            List<String> hwPathList = FileUtil.listHwName(courseName);
            List<FileItemVO> resList = new ArrayList<>();
            for (String s: hwPathList) {
                FileItemVO vo = new FileItemVO();
                vo.setPath(s);
                String[] pathArr = s.split("/");
                vo.setName(pathArr[pathArr.length - 1]);
                resList.add(vo);
            }
            return new Response<>(true, resList);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to homework ppt");
        }
    }
}
