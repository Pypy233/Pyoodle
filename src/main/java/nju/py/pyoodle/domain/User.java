package nju.py.pyoodle.domain;

import lombok.Data;
import nju.py.pyoodle.enumeration.UserType;

import javax.persistence.*;

/**
 * @Author: py
 * @Date: 2019/1/29 下午4:46
 * @Version 1.0
 */
@Entity(name = "user")
@Data
public class User {

    @Id
    private String name;

    private String password;

    @Column(name = "student_number")
    private String studentNumber;

    @Enumerated(EnumType.STRING)
    private UserType type;

    private String email;

    
}
