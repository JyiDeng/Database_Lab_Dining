package com.example.pj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class IndexController {

    @GetMapping({"", "/"})
    public String index() {
        return  "欢迎来到主索引页！<br>-------------------<br>" +
                "商家索引页：`http://localhost:8080/merchant/`<br>" +
                "用户索引页：`http://localhost:8080/user/`<br>" +
                "管理员索引页：`http://localhost:8080/admin/`<br>";
    }
}
