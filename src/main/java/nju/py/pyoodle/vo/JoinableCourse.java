package nju.py.pyoodle.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import nju.py.pyoodle.domain.Course;

/**
 * @Author: py
 * @Date: 2019/2/20 下午9:29
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class JoinableCourse {
    private String name;

    private String teacher;

    private String time;

    private int limit;

    private int current;

    public JoinableCourse(Course course) {
        this.setName(course.getName());
        this.setTime(course.getTime().toString());
        this.setCurrent(course.getCurrent());
        this.setTeacher(course.getTeacher().getName());
        this.setLimit(course.getLimit());
    }
}
