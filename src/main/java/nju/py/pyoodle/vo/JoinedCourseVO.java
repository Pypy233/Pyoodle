package nju.py.pyoodle.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import nju.py.pyoodle.domain.Course;

/**
 * @Author: py
 * @Date: 2019/3/8 下午2:18
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class JoinedCourseVO {
    private String name;

    private String teacher;

    private String time;

    private int limit;

    private int current;

    public JoinedCourseVO(Course course) {
        this.limit = course.getLimit();
        this.current = course.getCurrent();
        this.time = course.getTime().toString();
        this.teacher = course.getTeacher().getName();
        this.name = course.getName();
    }

}
