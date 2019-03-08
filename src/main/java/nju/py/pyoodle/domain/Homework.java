package nju.py.pyoodle.domain;

import lombok.Data;

import javax.persistence.*;
import java.lang.annotation.Documented;
import java.time.LocalDate;

/**
 * @Author: py
 * @Date: 2019/2/1 下午11:13
 * @Version 1.0
 */
@Entity(name = "homework")
@Data
public class Homework {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;

    private int size;

    private LocalDate beginDate;

    private LocalDate ddl;

    private String type;

}
