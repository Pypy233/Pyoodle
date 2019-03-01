package nju.py.pyoodle.service.Impl;

import nju.py.pyoodle.domain.CourseBase;
import nju.py.pyoodle.service.CourseBaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: py
 * @Date: 2019/2/28 下午6:37
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CourseBaseServiceImplTest {

    @Autowired
    private CourseBaseService courseBaseService;

    @Test
    public void listCourseBaseCheck() {
        List<CourseBase> courseBases = courseBaseService.listCourseBaseCheck().getData();
        System.out.println(courseBases.size());

    }
}