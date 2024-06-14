//package com.example.pj.controller;
//
//
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//

package com.example.pj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SuccessController {

    @GetMapping("/admin/{username}")
    public String adminSuccess(@PathVariable String username) {
        // 你可以根据username进行个性化处理
        return "adminSuccess"; // 返回admin success页面的视图名称
    }

    @GetMapping("/user/{username}")
    public String userSuccess(@PathVariable String username) {
        // 你可以根据username进行个性化处理
        return "userSuccess"; // 返回user success页面的视图名称
    }

    @GetMapping("/merchant/{username}")
    public String merchantSuccess(@PathVariable String username) {
        // 你可以根据username进行个性化处理
        return "merchantSuccess"; // 返回merchant success页面的视图名称
    }
}

//@Controller
//public class SuccessController {
//
//    @GetMapping("/admin/{username}")
//    public String adminSuccess(@PathVariable String username, Authentication authentication) {
//        if (authentication.getName().equals(username) && authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
//            return "adminSuccess"; // 返回admin success页面的视图名称
//        }
//        return "redirect:/login?error";
//    }
//
//    @GetMapping("/user/{username}")
//    public String userSuccess(@PathVariable String username, Authentication authentication) {
//        if (authentication.getName().equals(username) && authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
//            return "userSuccess"; // 返回user success页面的视图名称
//        }
//        return "redirect:/login?error";
//    }
//
//    @GetMapping("/merchant/{username}")
//    public String merchantSuccess(@PathVariable String username, Authentication authentication) {
//        if (authentication.getName().equals(username) && authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_MERCHANT"))) {
//            return "merchantSuccess"; // 返回merchant success页面的视图名称
//        }
//        return "redirect:/login?error";
//    }
//}
//
