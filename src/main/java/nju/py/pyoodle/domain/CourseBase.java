package nju.py.pyoodle.domain;

import lombok.Data;
import nju.py.pyoodle.enumeration.CourseState;

import javax.persistence.*;
import java.util.List;

/**
 * @Author: py
 * @Date: 2019/2/1 下午7:08
 * @Version 1.0
 */
@Entity(name = "course_base")
@Data
public class  CourseBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "pic_path")
    private String picPath;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User teacher;

    @Enumerated(EnumType.STRING)
    private CourseState state;


    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> pptList;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Homework> homeworkList;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HomeworkResult> homeworkResultList;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private BBS bbs;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ScoreElement>  scoreElementList;
}
