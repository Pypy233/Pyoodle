package nju.py.pyoodle.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import nju.py.pyoodle.enumeration.CourseState;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @Author: py
 * @Date: 2019/2/2 上午9:58
 * @Version 1.0
 */
@Entity(name = "course")
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private CourseBase courseBase;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User teacher;

    private LocalDate time;

    @Enumerated(EnumType.STRING)
    private CourseState state;

    @Column(name = "student_limit")
    private int limit;

    private int current;

    private int classNum;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Homework> homeworkList;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> students;

}
