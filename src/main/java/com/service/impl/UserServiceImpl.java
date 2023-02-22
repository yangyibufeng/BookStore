package com.service.impl;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.pojo.User;
import com.service.UserService;

/**
 * UserService的实现类
 *
 * @author yangyibufeng
 * @date 2023/2/6
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPasswd(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {
        return userDao.queryUserByUsername(username) != null;
    }
}