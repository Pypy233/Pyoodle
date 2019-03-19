package nju.py.pyoodle.service.Impl;

import nju.py.pyoodle.dao.UserDAO;
import nju.py.pyoodle.domain.User;
import nju.py.pyoodle.enumeration.UserType;
import nju.py.pyoodle.service.UserService;
import nju.py.pyoodle.util.Response;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public Response<Boolean> updateUser(String ordinaryName, String name, String studentNumber) {
        try {
            User user = userDAO.getUserByName(ordinaryName);
            user.setName(name);
            user.setStudentNumber(studentNumber);
            userDAO.save(user);
            return new Response<>(true, "");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "");
        }
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

    @Override
    public Response<Boolean> updateUser(String ordinaryUserName, String newUserName, String password, String email, String studentNumber) {
        try {
            User user = userDAO.getUserByName(ordinaryUserName);
            if (!(newUserName == null || newUserName.equals("")) ) {
                user.setName(newUserName);
            }
            user.setStudentNumber(studentNumber);
            user.setPassword(password);
            user.setEmail(email);
            userDAO.save(user);
            return new Response<>(true, "Succeed to update user info...");

        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "Fail to update user info...");
        }
    }

    @Override
    public Response<User> getStudentNum(String userName) {
        try {
            User user = userDAO.getUserByName(userName);
            return new Response<>(true, user);
        } catch (Exception ex) {
            return new Response<>(false, "");
        }
    }

    @Override
    public Response<Integer> getNum(UserType userType) {
        try {
            List<User> userList = userDAO.findAll();
            int count = 0;
            for(User user: userList) {
                if (user.getType().equals(userType)) {
                    count++;
                }
            }
            return new Response<>(true, count);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response<>(false, "");
        }
    }


}
