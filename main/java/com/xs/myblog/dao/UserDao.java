package com.xs.myblog.dao;


import com.xs.myblog.pojo.User;


public interface UserDao {

    User findByUsernameAndPassword(String username, String password);

    void updatePassword(Long id, String password);
}
