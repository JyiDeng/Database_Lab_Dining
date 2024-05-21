package com.example.pj.controller;

import com.example.pj.dao.UserMapper;
import com.example.pj.entity.User;
import com.example.pj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userServiceImpl;
    @Autowired
    UserMapper userMapper;

    @GetMapping({"/allUserList"})
    public List<User> userList() {
        return userMapper.findAll();
    }

    // 显示特定用户
    @GetMapping("/id={id}")
    public User getUserById(@PathVariable Long id) {
        return userMapper.findById(id);
    }

//    // 添加新用户
//    @PostMapping("/add")
//    public String addUser(@RequestBody User user) {
//        userMapper.addUser(user);
//        return "User added successfully";
//    }
//
//    // 更新用户信息
//    @PutMapping("/update")
//    public String updateUser(@RequestBody User user) {
//        userService.updateUser(user);
//        return "User updated successfully";
//    }
//
//    // 删除用户
//    @DeleteMapping("/delete/{id}")
//    public String deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return "User deleted successfully";
//    }

//
//
//    @RequestMapping("{page}")
//    public String showPage(@PathVariable String page){
//        return page;
//    }
//
//    //添加用户
//    @RequestMapping("/addUser")
//    public String addUser(User user){
//        this.userServiceImpl.addUser(user);
//        return "ok";
//    }

//    @GetMapping("/add/{name}/{age}")
//    public String add(HttpServletRequest request, @PathVariable String name, @PathVariable Integer age) {
//        User model = new User();
//        model.setName(name);
//        model.setAge(age);
//
//        int res = userService.save(model);
//        return String.valueOf(res);
//    }

//    @PostMapping("/loginCheck.do")
//    @ResponseBody
//    public String login(String userID,String password, HttpServletRequest request) throws IOException {
//        User user=userService.checkLogin(userID,password);
//        if(user!=null){
//            if(user.getIsadmin()==0){
//                request.getSession().setAttribute("user",user);
//                System.out.println("user login!");
//                return "/index";
//            }
//            else if(user.getIsadmin()==1){
//                request.getSession().setAttribute("admin",user);
//                System.out.println("admin login!");
//                return "/admin_index";
//            }
//        }
//        return "false";
//
//    }
    @PostMapping("/hello")
    @ResponseBody
    public String hello(Model model){
        model.addAttribute("str","hello!");
        return "hello";
    }
}
