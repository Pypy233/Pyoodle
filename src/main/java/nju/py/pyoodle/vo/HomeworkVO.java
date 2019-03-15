package nju.py.pyoodle.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nju.py.pyoodle.domain.Homework;

/**
 * @Author: py
 * @Date: 2019/3/15 下午2:29
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeworkVO {
    private String name;

    private String ddl;

    private String description;

    public HomeworkVO(Homework homework) {
        this.name = homework.getName();
        this.ddl = homework.getDdl().toString();
        this.description = homework.getDescription();
    }
}
