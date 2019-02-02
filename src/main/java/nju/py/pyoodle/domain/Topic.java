package nju.py.pyoodle.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @Author: py
 * @Date: 2019/2/1 下午11:26
 * @Version 1.0
 */
@Entity(name = "topic")
@Data
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private LocalDate time;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    private String content;
}
