package com.example.pj.entity;

//package com.example.demo.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Component
public class LoginRequest {
    private String type;
    private String username;
    private String password;

    // getters and setters
}
