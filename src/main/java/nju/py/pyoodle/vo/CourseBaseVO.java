package nju.py.pyoodle.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nju.py.pyoodle.domain.CourseBase;

/**
 * @Author: py
 * @Date: 2019/2/28 下午6:39
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseBaseVO {
    private String name;

    private String teacherName;

    public CourseBaseVO(CourseBase courseBase) {
        this.name = courseBase.getName();
        this.teacherName = courseBase.getTeacher().getName();
    }

}
