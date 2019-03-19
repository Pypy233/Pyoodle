package nju.py.pyoodle.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import nju.py.pyoodle.domain.Course;
import nju.py.pyoodle.util.FileUtil;

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

    private String picPath;

    public CourseVO(Course course) {
        this.name = course.getName();
        this.teacherName = course.getTeacher().getName();
        this.time = course.getTime().toString();
        if (FileUtil.listPicName(course.getName()) != null && FileUtil.listPicName(course.getName()).size() != 0) {
            this.picPath = FileUtil.listPicName(course.getName()).get(0);
        }else this.picPath = "";
    }

}
