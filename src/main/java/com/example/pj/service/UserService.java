package com.example.pj.service;

import com.example.pj.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> index();
    List<User> findAllUsers();

    User findUserById(Long id);

//    void addUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    Integer save(User user);


}
