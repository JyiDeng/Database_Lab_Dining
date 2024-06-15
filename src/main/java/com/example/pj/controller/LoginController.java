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
    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = loginService.authenticate(loginRequest.getType(), loginRequest.getUserId(), loginRequest.getPassword());

        if (isAuthenticated) {
            String redirectUrl;
            switch (loginRequest.getType()) {
                case "admin":
                    redirectUrl = "/admin/" + loginRequest.getUserId();
                    break;
                case "user":
                    redirectUrl = "/user/" + loginRequest.getUserId();
//                    redirectUrl = loginRequest.getUsername();
                    break;
                case "merchant":
                    redirectUrl = "/merchant/" + loginRequest.getUserId();
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

