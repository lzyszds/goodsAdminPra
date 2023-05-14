package com.example.demo005.service.impl;

import com.example.demo005.domain.User;
import com.example.demo005.mapper.UserMapper;
import com.example.demo005.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserName(String username) {
        return userMapper.selectUserName(username);
    }

    @Override
    public User selectUser(String username, String password) {
        return userMapper.selectUser(username, password);
    }

    //    public User[] queryAllUsers() {
//        return userMapper.queryAllUsers();
//    }
    @Override
    public User[] querySearchUsers(String search) {
        return userMapper.querySearchUsers(search);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int updateUserStatus(String username, int status) {
        return userMapper.updateUserStatus(username, status);
    }

    @Override
    public int deleteUser(int id) {
        return userMapper.deleteUser(id);
    }


}
