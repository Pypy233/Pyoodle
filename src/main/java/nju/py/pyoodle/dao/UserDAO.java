package nju.py.pyoodle.dao;

import nju.py.pyoodle.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: py
 * @Date: 2019/1/30 上午2:21
 * @Version 1.0
 */
public interface UserDAO extends JpaRepository<User, Integer>{
    User getUserByName(String username);

}
