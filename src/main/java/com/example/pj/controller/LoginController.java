package com.example.pj.controller;

import com.example.pj.entity.LoginRequest;
import com.example.pj.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = loginService.authenticate(loginRequest.getType(), loginRequest.getUsername(), loginRequest.getPassword());

        if (isAuthenticated) {
//            return ResponseEntity.ok("Login successful");
            String redirectUrl;
            switch (loginRequest.getType()) {
                case "admin":
                    redirectUrl = "/admin/success";
                    break;
                case "user":
                    redirectUrl = "/user/success";
                    break;
                case "merchant":
                    redirectUrl = "/merchant/success";
                    break;
                default:
                    return ResponseEntity.status(400).body("Invalid login type");
            }
            return ResponseEntity.ok().header("Location", redirectUrl).build();
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
