package nju.py.pyoodle.service.Impl;

import nju.py.pyoodle.dao.UserDAO;
import nju.py.pyoodle.domain.User;
import nju.py.pyoodle.service.UserService;
import nju.py.pyoodle.util.Response;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: py
 * @Date: 2019/1/31 下午2:17
 * @Version 1.0
 */
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean saveUser(String username, String pwd) {
        if(userDAO.getUserByName(username)!= null) {
            return false;
        }
        User user = new User();
        user.setName(username);
        user.setPassword(pwd);
        userDAO.save(user);
        return true;
    }

    @Override
    public void saveUser(User user) {
        userDAO.save(user);
    }

    @Override
    public Response<Boolean> canLogin(String username, String pwd) {
        User user = userDAO.getUserByName(username);
       // System.out.println(user.getName());
        if(user == null) {
            return new Response<>(false, null, "Fail to find user...");
        }
        System.out.println("*******");
        System.out.println(username);
        System.out.println(pwd);
        if(user.getPassword().equals(pwd)) {
            return new Response<>(true, "Succeed to index.html..");
        }
        else {

            return new Response<>(false, "Password error...");
        }
    }

    @Override
    public Response<Boolean> updateUser(String name, String studentNumber) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    @Override
    public User indByConfirmationToken(String confirmationToken) {
        return userDAO.getUserByConfirmationToken(confirmationToken);
    }
}
