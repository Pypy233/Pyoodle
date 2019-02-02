package nju.py.pyoodle.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author: py
 * @Date: 2019/2/1 下午11:55
 * @Version 1.0
 */
@Entity(name = "homework_result")
@Data
public class HomeworkResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User student;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Homework homework;

    private double score;
}
