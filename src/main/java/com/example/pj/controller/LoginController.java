package com.example.pj.controller;

import com.example.pj.entity.LoginRequest;
import com.example.pj.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
//
//
//    @GetMapping("/adminLogin")
//    public String adminLogin() {
//        return "adminLogin"; // 返回页面名称，会自动在 templates 目录下寻找 adminLogin.html
//    }
//
//    @GetMapping("/userLogin")
//    public String userLogin() {
//        return "login2"; // 返回页面名称，会自动在 templates 目录下寻找 userLogin.html
//    }
//
//    @GetMapping("/merchantLogin")
//    public String merchantLogin() {
//        return "merchantLogin"; // 返回页面名称，会自动在 templates 目录下寻找 merchantLogin.html
//    }



//
    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = loginService.authenticate(loginRequest.getType(), loginRequest.getUsername(), loginRequest.getPassword());

        if (isAuthenticated) {
            String redirectUrl;
            switch (loginRequest.getType()) {
                case "admin":
                    redirectUrl = "/admin/" + loginRequest.getUsername();
                    break;
                case "user":
                    redirectUrl = "/user/" + loginRequest.getUsername();
//                    redirectUrl = loginRequest.getUsername();
                    break;
                case "merchant":
                    redirectUrl = "/merchant/" + loginRequest.getUsername();
                    break;
                default:
                    return ResponseEntity.status(400).body(null);
            }
            return ResponseEntity.ok().body(Collections.singletonMap("redirectUrl",redirectUrl));
//            return ResponseEntity.status(302).header("Location", redirectUrl).build();
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}


//@RestController
//public class LoginController {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    UserMapper userMapper;
//    @PostMapping("/login")
//    public Map<String, Object> login(@RequestParam String username, @RequestParam String password, HttpSession session) {
//        Map<String, Object> response = new HashMap<>();
//        User user = userMapper.findByUsername(username);
//
//        if (user != null && user.getPassword().equals(password)) {
//            session.setAttribute("username", username);
//            response.put("success", true);
//            switch (username) {
//                case "admin":
//                    response.put("redirect", "/admin_page");
//                    break;
//                case "Alice1":
//                case "user2":
//                    response.put("redirect", "/user_page");
//                    break;
//                case "merchant1":
//                case "merchant2":
//                    response.put("redirect", "/merchant_page");
//                    break;
//                default:
//                    response.put("redirect", "/error");
//            }
//        } else {
//            response.put("success", false);
//        }
//
//        return response;
//    }
//}
