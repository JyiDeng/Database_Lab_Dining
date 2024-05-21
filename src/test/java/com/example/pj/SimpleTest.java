package com.example.pj;

import com.example.pj.dao.UserDao;
import com.example.pj.po.User;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class SimpleTest {

    private UserDao userDao;

    @BeforeEach
    public void init() throws IOException {
        userDao = new UserDao("mybatis-config.xml");
    }

    @Test
    public void insertTest() {
        User user = new User();
        user.setUserID(3);
        user.setUsrName("user2_InsertedBySimpleTest");
        user.setEcardID(87);
        user.setRole("Student");
        user.setGender("Female");
        user.setAge(20);

        userDao.addUser(user);
        List<User> all = userDao.findAll();
        all.forEach(System.out::println);
    }

    @Test
    public void findAllTest() {

    }
}
