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

    @GetMapping({"", "/", "index"})
    public String index() {
        return "欢迎来到管理员索引页！<br>以下为操作示例：<br>-------------------<br>" +
                "查询所有用户列表：`http://localhost:8080/admin/allUserList`<br>" +
                "增加id为4的用户，名字为jill，性别为Female，学工号为12321，角色为Student，年龄为20（如果已有这个id的用户会给出提示信息）：`http://localhost:8080/admin/addUser?id=4&userName=Jill&gender=Female&ecardId=12321&role=Student&age=20`<br>" +
                "更新id为1的用户的名字为JohnDoe，性别为Male，学工号为12345，角色为Staff，年龄为30（如果不存在这个id的用户会给出提示信息）：`http://localhost:8080/admin/updateUser?id=1&userName=JohnDoe&gender=Male&ecardId=12345&role=Staff&age=30`<br>" +
                "删除id为4的用户（如果不存在这个id的用户会给出提示信息）：`http://localhost:8080/admin/deleteUser/4`<br>" +
                "<br>-------------------<br>" +
                "商家索引页：`http://localhost:8080/merchant/`<br>" +
                "用户索引页：`http://localhost:8080/user/`<br>";

    }

    @GetMapping({"/allUserList"})
    public List<User> userList() {
        return userMapper.findAll();
    }


    @RequestMapping("/addUser")
    public String addUser(@RequestParam Long id, String userName, String gender, Long ecardId, String role, Integer age) {
        if (userMapper.findByID(id) == null){
            User newUser = new User(id,userName,gender,ecardId,role,age);
            userMapper.insert(newUser);
            return "User" + id + " is added successfully!";
        }else{
            return "User" + id + " already exists!";
        }
    }

    @RequestMapping("/updateUser")
    public String updateUser(@RequestParam Long id, String userName, String gender, Long ecardId, String role, Integer age) {
        if (userMapper.findByID(id) != null){
//            userMapper.delete(id);
//            User newUser = new User(id,userName,gender,ecardId,role,age);
            User currentUser = userMapper.findByID(id);
            currentUser.setUserName(userName);
            currentUser.setGender(gender);
            currentUser.setEcardID(ecardId);
            currentUser.setRole(role);
            currentUser.setAge(age);
            userMapper.update(currentUser);
            return "User" + id + " is updated successfully!";
        }else{
            return "User" + id + " does not exist!";
        }
    }


    @RequestMapping("/deleteUser/{id}")

    public String deleteUser(@PathVariable Long id) {
        if (userMapper.findByID(id) != null){
            userMapper.delete(id);
            return "User" + id + " is deleted successfully!";
        }else{
            return "User" + id + " does not exist!";
        }
    }

}
