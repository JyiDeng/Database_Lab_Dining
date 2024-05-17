package com.example.pj.controller;

import com.example.pj.entity.User;
import com.example.pj.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    @RequestMapping("{page}")
    public String showPage(@PathVariable String page){
        return page;
    }
    //添加用户
    @RequestMapping("/addUser")
    public String addUser(User user){
        this.userServiceImpl.addUser(user);
        return "ok";
    }

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
