package com.example.pj.controller;

import com.example.pj.entity.Merchant;
import com.example.pj.service.MerchantService;
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
        return "欢迎来到商家索引页！<br>以下为操作示例：<br>-------------------<br>" +
                "TODO" +
                "<br>-------------------<br>" +
                "用户索引页：`http://localhost:8080/user/`<br>" +
                "管理员索引页：`http://localhost:8080/admin/`<br>";

    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(Model model){
        model.addAttribute("str","hello!");
        return "hello";
    }
}
