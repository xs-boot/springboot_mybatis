package com.xs.myblog.service.impl;


import com.xs.myblog.dao.UserDao;
import com.xs.myblog.pojo.User;
import com.xs.myblog.service.UserService;
import com.xs.myblog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        return userDao.findByUsernameAndPassword(username, MD5Utils.code(password));
    }

    @Override
    public void updatePassword(Long id, String password) {
        userDao.updatePassword(id, password);
    }
}
