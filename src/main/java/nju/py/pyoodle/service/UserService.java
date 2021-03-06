package nju.py.pyoodle.service;

import nju.py.pyoodle.domain.User;
import nju.py.pyoodle.enumeration.UserType;
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

    Response<UserType> canLogin(String username, String pwd);

    Response<Boolean> updateUser(String ordinaryName, String name, String studentNumber);

    User findByEmail(String email);

    User findByConfirmationToken(String confirmationToken);

    boolean updateInfo(String username, String password, String emil, String number);

    Response<Boolean> updateUser(String ordinaryUserName, String newUserName, String password, String email, String studentNumber);

    Response<User> getStudentNum(String userName);

    Response<Integer> getNum(UserType userType);
}
