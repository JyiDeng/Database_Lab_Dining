package com.example.pj.service.Impl;

import com.example.pj.controller.UserController;
import com.example.pj.dao.UserMapper;
import com.example.pj.po.User;
import com.example.pj.service.UserService;
import jakarta.transaction.Transactional;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    @Mapper
    private UserMapper userMapper;

//    @Override
//    public User checkLogin(String userID, String password) {
//        return null;
//    }

    @Override
    public void addUser(User user){
        this.userMapper.insertUser(user);
    }


}
