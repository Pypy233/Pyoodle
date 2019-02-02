package nju.py.pyoodle.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @Author: py
 * @Date: 2019/2/1 下午11:23
 * @Version 1.0
 */
@Entity(name = "bbs")
@Data
public class BBS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Topic> topicList;
}
