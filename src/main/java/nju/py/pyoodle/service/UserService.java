package nju.py.pyoodle.service;

import nju.py.pyoodle.domain.User;
import nju.py.pyoodle.util.Response;
import org.springframework.stereotype.Service;

/**
 * @Author: py
 * @Date: 2019/1/30 上午2:22
 * @Version 1.0
 */
@Service
public interface UserService {

    boolean saveUser(String username, String pwd);

    void saveUser(User user);

    Response<Boolean> canLogin(String username, String pwd);

    Response<Boolean> updateUser(String name, String studentNumber);

    User findByEmail(String email);

    User indByConfirmationToken(String confirmationToken);

}
