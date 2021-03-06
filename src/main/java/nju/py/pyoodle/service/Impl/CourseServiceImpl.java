package nju.py.pyoodle.service.Impl;

import nju.py.pyoodle.dao.*;
import nju.py.pyoodle.domain.*;
import nju.py.pyoodle.enumeration.CourseState;
import nju.py.pyoodle.enumeration.UserType;
import nju.py.pyoodle.service.CourseService;
import nju.py.pyoodle.util.FileUtil;
import nju.py.pyoodle.util.Response;
import nju.py.pyoodle.vo.CourseVO;
import nju.py.pyoodle.vo.FileItemVO;
import nju.py.pyoodle.vo.JoinableCourse;
import nju.py.pyoodle.vo.JoinedCourseVO;
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

    private final BBSDAO bbsDAO;

    private final DroppedCourseDAO droppedCourseDAO;

    @Autowired
    public CourseServiceImpl(CourseDAO courseDAO, UserDAO userDAO, CourseBaseDAO courseBaseDAO, BBSDAO bbsDAO, DroppedCourseDAO droppedCourseDAO) {
        this.courseDAO = courseDAO;
        this.userDAO = userDAO;
        this.courseBaseDAO = courseBaseDAO;
        this.bbsDAO = bbsDAO;
        this.droppedCourseDAO = droppedCourseDAO;
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
            BBS bbs = new BBS();
            bbs.setCourse(course);
            bbsDAO.save(bbs);

            return new Response<>(true, "Succeed to save course...");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to save course...");
        }
    }

    @Override
    public Response<List<CourseVO>> listPassedCourse() {
        try {
            List<CourseVO> courseList = new ArrayList<>();
            for (Course c: courseDAO.getCoursesByState(CourseState.Success)) {
                courseList.add(new CourseVO(c));
            }
            return new Response<>(true, courseList);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to list passed course...");
        }
    }

    @Override
    public Response<Boolean> joinCourse(List<String> courseNameList) {
        try {
            String userName = courseNameList.get(0);
            for (int i = 1; i < courseNameList.size(); i++) {
                Course course = courseDAO.getCourseByName(courseNameList.get(i));
                List<User> studentList = course.getStudents();

                User user = userDAO.getUserByName(userName);
                if (!studentList.contains(user)) {
                    studentList.add(user);
                    course.setStudents(studentList);
                    courseDAO.save(course);
                }
            }
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
            ex.printStackTrace();
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
            Course course = courseDAO.getCourseByName(courseName);
            List<Homework> homeworkList = course.getHomeworkList();
            List<FileItemVO> resList = new ArrayList<>();
            for (Homework h: homeworkList) {
                FileItemVO vo = new FileItemVO();
                vo.setName(h.getName());
                resList.add(vo);
            }
            return new Response<>(true, resList);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to homework ppt");
        }
    }

    @Override
    public Response<Boolean> dropCourse(List<String> idCourseList) {
        try {
            String userName = idCourseList.get(0);
            for (int i = 1; i < idCourseList.size(); i++) {
                Course course = courseDAO.getCourseByName(idCourseList.get(i));
                List<User> studentList = course.getStudents();

                User user = userDAO.getUserByName(userName);
                if (studentList.contains(user)) {
                    studentList.remove(user);
                    course.setStudents(studentList);
                    courseDAO.save(course);
                    DroppedCourse droppedCourse = new DroppedCourse();
                    droppedCourse.setCourseName(course.getName());
                    droppedCourse.setUser(user);
                    droppedCourseDAO.save(droppedCourse);
                }
            }
            return new Response<>(true, "Succeed to drop course...");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to drop course...");
        }
    }

    @Override
    public Response<List<JoinedCourseVO>> listJoinedCourse(String userName) {
        try {
            List<JoinedCourseVO> voList = new ArrayList<>();
            List<Course> courseList = courseDAO.findAll();
            User user = userDAO.getUserByName(userName);
            if (user.getType().equals(UserType.TEACHER)) {
                List<Course> courseList1 = courseDAO.getCoursesByTeacher(user);
                for(Course course: courseList1) {
                    voList.add(new JoinedCourseVO(course));
                }
            } else {
                for (Course course : courseList) {
                    if ( course.getStudents().contains(user) ) {
                        voList.add(new JoinedCourseVO(course));
                    }
                }
            }
            return new Response<>(true, voList);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to list joined course...");
        }
    }

    @Override
    public Response<List<String>> listCourseByTeacher(String userName) {
        try {
            User user = userDAO.getUserByName(userName);
            List<Course> courseList = courseDAO.getCoursesByTeacher(user);
            List<String> stringList = new ArrayList<>();
            for(Course course: courseList) {
                stringList.add(course.getName());
            }
            System.out.println(stringList.size());
            return new Response<>(true, stringList);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to list course by teacher");
        }
    }

    @Override
    public Response<List<JoinedCourseVO>> listCourse(String userName) {
        try {
            User user = userDAO.getUserByName(userName);
            List<Course> courseList = courseDAO.getCoursesByTeacher(user);
            List<JoinedCourseVO> joinableCourseList = new ArrayList<>();
            for(Course course: courseList) {
                joinableCourseList.add(new JoinedCourseVO(course));
            }
            return new Response<>(true, joinableCourseList);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to list joined");
        }
    }
}
