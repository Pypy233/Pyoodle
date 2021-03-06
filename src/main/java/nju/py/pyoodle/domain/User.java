package nju.py.pyoodle.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import nju.py.pyoodle.enumeration.UserType;

import javax.persistence.*;
import java.util.List;

/**
 * @Author: py
 * @Date: 2019/1/29 下午4:46
 * @Version 1.0
 */
@Entity(name = "user")
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class User {

    @Id
    private String name;

    private String password;

    @Column(name = "student_number")
    private String studentNumber;

    @Enumerated(EnumType.STRING)
    private UserType type;

    private String email;

    @Basic
    private int enabled;

    @Column(name = "confirmation_token")
    private String confirmationToken;


}
