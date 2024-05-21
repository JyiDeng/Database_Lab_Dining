package com.example.pj.service.Impl;

import com.example.pj.dao.UserMapper;
import com.example.pj.entity.User;
import com.example.pj.service.UserService;
import jakarta.transaction.Transactional;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    @Mapper
    private UserMapper userMapper;

    @Override
    public List<User> index() {
        return userMapper.findAll();
    }
    @Override
    public List<User> findAllUsers() {
        return userMapper.findAll();
    }

    @Override
    public Integer save(User user) {
        return userMapper.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.update(user);
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.delete(id);
    }




}
