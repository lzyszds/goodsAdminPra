package com.example.demo005.service;

import com.example.demo005.domain.User;


public interface UserService {
    User selectUserName(String username);

    User selectUser(String username, String password);

    User[] querySearchUsers(String search);

    int insertUser(User user);

    int updateUser(User user);

    int updateUserStatus(String username, int status);

    int deleteUser(int id);

}