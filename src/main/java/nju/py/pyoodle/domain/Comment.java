package nju.py.pyoodle.domain;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @Author: py
 * @Date: 2019/2/1 下午11:46
 * @Version 1.0
 */
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    private LocalDate time;



    
}
