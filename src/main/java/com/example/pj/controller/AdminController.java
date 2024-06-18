package com.example.pj.controller;

import com.example.pj.entity.User;
import com.example.pj.mapper.MerchantMapper;
import com.example.pj.mapper.UserMapper;
import com.example.pj.service.MerchantService;
import com.example.pj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    MerchantMapper merchantMapper;


    @GetMapping("/{path}/allUserList")
    public List<User> userList(@PathVariable String path) {
        return userMapper.findAll();
    }

    @RequestMapping("/{path}/addUser")
    public String addUser(@RequestParam Long id, String userName, String gender, Long ecardId, String role, Integer age,String password, @PathVariable String path) {
        if (userMapper.findByID(id) == null){
            User newUser = new User(id,userName,gender,ecardId,role,age,password);
            userMapper.insert(newUser);
            return "User" + id + " is added successfully!";
        }else{
            return "User" + id + " already exists!";
        }
    }

    @RequestMapping("/{path}/updateUser")
    public String updateUser(@RequestParam Long id, String userName, String gender, Long ecardId, String role, Integer age, @PathVariable String path) {
        if (userMapper.findByID(id) != null){
            User currentUser = userMapper.findByID(id);
            currentUser.setUserName(userName);
            currentUser.setGender(gender);
            currentUser.setEcardId(ecardId);
            currentUser.setRole(role);
            currentUser.setAge(age);
            userMapper.update(currentUser);
            return "User" + id + " is updated successfully!";
        }else{
            return "User" + id + " does not exist!";
        }
    }


    @RequestMapping("/{path}/deleteUser/{id}")

    public String deleteUser(@PathVariable Long id, @PathVariable String path) {
        if (userMapper.findByID(id) != null){
            userMapper.delete(id);
            return "User" + id + " is deleted successfully!";
        }else{
            return "User" + id + " does not exist!";
        }
    }

}
