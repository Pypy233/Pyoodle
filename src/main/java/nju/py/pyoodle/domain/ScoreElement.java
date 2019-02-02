package nju.py.pyoodle.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author: py
 * @Date: 2019/2/1 下午7:08
 * @Version 1.0
 */
@Entity(name = "score_element")
@Data
public class ScoreElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User student;

    private double score;
}
