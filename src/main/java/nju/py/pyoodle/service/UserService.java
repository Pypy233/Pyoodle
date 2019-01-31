package nju.py.pyoodle.service;

import nju.py.pyoodle.util.Response;
import org.springframework.stereotype.Service;

/**
 * @Author: py
 * @Date: 2019/1/30 上午2:22
 * @Version 1.0
 */
@Service
public interface UserService {

    Response<Boolean> addUser(String username, String pwd, String email);

    Response<Boolean> canLogin(String username, String pwd);

    Response<Boolean> updateUser(String name, String studentNumber);

}
