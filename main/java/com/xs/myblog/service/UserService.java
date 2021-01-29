package com.xs.myblog.service;


import com.xs.myblog.pojo.User;

public interface UserService {

    User checkUser(String username, String password);

    void updatePassword(Long id, String password);
}
