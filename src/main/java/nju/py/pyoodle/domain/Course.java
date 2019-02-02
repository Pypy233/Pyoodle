package nju.py.pyoodle.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @Author: py
 * @Date: 2019/2/2 上午9:58
 * @Version 1.0
 */
@Entity(name = "course")
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CourseBase courseBase;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User teacher;

    private LocalDate time;

    @Column(name = "student_limit")
    private int limit;

    private int classNum;
}
