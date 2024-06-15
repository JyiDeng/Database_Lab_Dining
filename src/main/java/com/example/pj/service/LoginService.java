package com.example.pj.service;


import com.example.pj.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginMapper loginMapper;

    public boolean authenticate(String type, String userId, String password) {
        switch (type) {
            case "admin":
                return loginMapper.validateAdmin(Long.parseLong(userId), password);
            case "user":
                return loginMapper.validateUser(Long.parseLong(userId), password);
            case "merchant":
                return loginMapper.validateMerchant(Long.parseLong(userId), password);
            default:
                throw new IllegalArgumentException("Invalid login type");
        }
    }
}
