package com.example.pj.service;

import com.example.pj.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User checkLogin(String userID, String password);
    public void addUser(User users);
}
