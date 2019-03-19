package nju.py.pyoodle.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

/**
 * @Author: py
 * @Date: 2019/3/18 上午2:33
 * @Version 1.0
 */
@Entity(name = "dropped_course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroppedCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String courseName;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    User user;
}
