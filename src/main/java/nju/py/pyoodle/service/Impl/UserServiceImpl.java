package nju.py.pyoodle.service.Impl;

import nju.py.pyoodle.dao.UserDAO;
import nju.py.pyoodle.domain.User;
import nju.py.pyoodle.enumeration.UserType;
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
    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

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
    public Response<UserType> canLogin(String username, String pwd) {
        User user = userDAO.getUserByName(username);
       // System.out.println(user.getName());
        if(user == null) {
            return new Response<>(false, null, "Fail to find user...");
        }
        if(user.getPassword().equals(pwd) && user.getEnabled() == 1) {
            return new Response<>(true, user.getType(), "Succeed to index.html..");
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
    public User findByConfirmationToken(String confirmationToken) {
        return userDAO.getUserByConfirmationToken(confirmationToken);
    }

    @Override
    public boolean updateInfo(String username, String password, String emil, String number) {
        User user = userDAO.getUserByName(username);

        return false;
    }
}
