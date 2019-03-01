package nju.py.pyoodle.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import nju.py.pyoodle.domain.Course;

/**
 * @Author: py
 * @Date: 2019/3/1 下午2:50
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class CourseVO {

    private String name;

    private String teacherName;

    private String time;

    public CourseVO(Course course) {
        this.name = course.getName();
        this.teacherName = course.getTeacher().getName();
        this.time = course.getTime().toString();
    }

}
