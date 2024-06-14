package com.example.pj.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    @GetMapping("/admin_page")
    public String adminPage(HttpSession session, Model model) {
        if (!"admin".equals(session.getAttribute("username"))) {
            return "myerror1";
        }
        model.addAttribute("username", session.getAttribute("username"));
        return "admin_page";
    }

    @GetMapping("/user_page")
    public String userPage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (!"user1".equals(username) && !"user2".equals(username)) {
            return "myerror1";
        }
        model.addAttribute("username", username);
        return "user_page";
    }

    @GetMapping("/merchant_page")
    public String merchantPage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (!"merchant1".equals(username) && !"merchant2".equals(username)) {
            return "myerror1";
        }
        model.addAttribute("username", username);
        return "merchant_page";
    }

    @GetMapping("/error")
    public String errorPage() {
        return "myerror1";
    }
}

