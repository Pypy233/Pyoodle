package nju.py.pyoodle.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Author: py
 * @Date: 2019/3/17 下午1:12
 * @Version 1.0
 */
@Entity(name = "score")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Course course;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User student;
}
