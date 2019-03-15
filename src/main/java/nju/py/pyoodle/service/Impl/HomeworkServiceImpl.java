package nju.py.pyoodle.service.Impl;

import nju.py.pyoodle.dao.CourseDAO;
import nju.py.pyoodle.dao.HomeworkDAO;
import nju.py.pyoodle.domain.Course;
import nju.py.pyoodle.domain.Homework;
import nju.py.pyoodle.domain.User;
import nju.py.pyoodle.service.HomeworkService;
import nju.py.pyoodle.util.DateUtil;
import nju.py.pyoodle.util.Response;
import nju.py.pyoodle.vo.HomeworkVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: py
 * @Date: 2019/2/16 下午2:56
 * @Version 1.0
 */
@Component
public class HomeworkServiceImpl implements HomeworkService {
    private final CourseDAO courseDAO;
    private final HomeworkDAO homeworkDAO;

    @Autowired
    public HomeworkServiceImpl(CourseDAO courseDAO, HomeworkDAO homeworkDAO) {
        this.courseDAO = courseDAO;
        this.homeworkDAO = homeworkDAO;
    }

    @Override
    public Response<Boolean> addHw(String name, String courseName, int size, String type, String ddl, String description) {
        try {
            Homework homework = new Homework();
            homework.setName(name);
            homework.setBeginDate(DateUtil.getNow());
            homework.setDdl(DateUtil.parse(ddl));
            homework.setDescription(description);
            homework.setSize(size);
            homework.setType(type);
            homeworkDAO.save(homework);

            Course course = courseDAO.getCourseByName(courseName);
            List<Homework> homeworkList = course.getHomeworkList();
            homeworkList.add(homework);
            course.setHomeworkList(homeworkList);
            courseDAO.save(course);
            return new Response<>(true, "Succeed to save homework");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to save homework");
        }
    }

    @Override
    public Response<Boolean> downloadHws(String courseName, String hwName) {
        try {
            Course course = courseDAO.getCourseByName(courseName);
            List<User> studentList = course.getStudents();

            //TODO
            return new Response<>(true, "Succeed to download all hws");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to download all hws...");
        }
    }

    @Override
    public Response<HomeworkVO> getHwByCourseAndName(String courseName, String hwName) {
        try {
            Course course = courseDAO.getCourseByName(courseName);
            List<Homework> homeworkList = course.getHomeworkList();
            for (Homework hw : homeworkList) {
                if ( hw.getName().equals(hwName) || hwName.contains(hw.getName())) {
                    return new Response<>(true, new HomeworkVO(hw));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new Response<>(false, "Fail to get hw...");
    }
}
