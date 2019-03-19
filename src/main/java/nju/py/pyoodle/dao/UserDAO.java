package nju.py.pyoodle.dao;

import nju.py.pyoodle.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: py
 * @Date: 2019/1/30 上午2:21
 * @Version 1.0
 */
public interface UserDAO extends JpaRepository<User, String>{
    User getUserByName(String username);

    User getUserByEmail(String email);

    User getUserByConfirmationToken(String token);

    User getUserByStudentNumber(String studentNumber);


}
