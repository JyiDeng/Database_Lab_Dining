package com.example.pj.service;

import com.example.pj.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

//    List<User> index();
    List<User> findAll();

    User findByID(Long id);

//    void addUser(User user);

//    void updateUser(User user);

    void delete(Long id);

//    Integer save(User user);


}
