package com.example.pj.controller;

import com.example.pj.dao.UserMapper;
import com.example.pj.entity.Merchant;
import com.example.pj.entity.User;
import com.example.pj.service.MerchantService;
import com.example.pj.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @GetMapping("/search")
    public List<Merchant> searchMerchant(@RequestParam String keyword) {
        return merchantService.searchMerchant(keyword);
    }

    @GetMapping({"", "/", "index"})
    public String index() {
        return "Welcome to **Merchant** Page!\nEnter in the url \"search\" to let user search merchant...(Strange logic).";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(Model model){
        model.addAttribute("str","hello!");
        return "hello";
    }
}
