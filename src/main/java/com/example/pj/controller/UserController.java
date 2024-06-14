package com.example.pj.controller;

import com.example.pj.entity.Dish;
import com.example.pj.mapper.DishMapper;
import com.example.pj.mapper.MerchantMapper;
import com.example.pj.mapper.UserMapper;
import com.example.pj.entity.Merchant;
import com.example.pj.entity.User;
import com.example.pj.service.MerchantService;
import com.example.pj.service.UserService;
import com.example.pj.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
// 如果用Controller才能用html加载，如果用RestController才能用文字加载
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    MerchantMapper merchantMapper;
    @Autowired
    private DishService dishService;
    @Autowired
    DishMapper dishMapper;
    @GetMapping({"", "/", "index"})
    public String index() {
        return "欢迎来到用户索引页！<br>以下为操作示例：<br>-------------------<br>" +
                "用户索引页：`http://localhost:8080/user/`<br>" +
                "查询第1个用户信息：`http://localhost:8080/user/id=1`<br>" +
                "查询所有商家列表（简略）：`http://localhost:8080/user/searchMerchant?keyword=`<br>" +
                "查询第2个商家列表（简略）：`http://localhost:8080/user/searchMerchant?keyword=2`<br>" +
                "查询第3个商家列表（详细）：`http://localhost:8080/user/searchMerchantDetails?id=3`<br>" +
                "查询第3个商家的关于`p`的菜品：`http://localhost:8080/user/searchDishes?merchantId=3&keyword=p`<br>" +
                "<br>-------------------<br>" +
                "商家索引页：`http://localhost:8080/merchant/`<br>" +
                "管理员索引页：`http://localhost:8080/admin/`<br>";
    }
    @RequestMapping("/login")
    private String index2() {
        return "login.html";
    }


    // 显示特定用户
    @GetMapping("/id={id}")
    public User getUserByID(@PathVariable Long id) {
        return userMapper.findByID(id);
    }

    @GetMapping("/searchMerchant")
    public List<Merchant> searchMerchant(@RequestParam String keyword) {
        return merchantMapper.searchMerchant(keyword);
    }

    @GetMapping("/searchMerchant2")
    public String searchMerchant2(@RequestParam String keyword) {
        return "user_search";
    }

    @GetMapping("/searchMerchantDetails")
    public Merchant getMerchantDetails(@RequestParam Long id, Model model) {
        return merchantMapper.getMerchantDetails(id);
//        Merchant merchant = merchantMapper.getMerchantByID(id);
////        List<Dish> dishes = merchantMapper.getDishesByMerchantID(id);
//        model.addAttribute("merchant", merchant);
////        model.addAttribute("dishes", dishes);
//        return "merchant_details";
    }

    @GetMapping("/searchDishes")
    public List<Dish> searchDishes(@RequestParam Long merchantId, @RequestParam String keyword/*, Model model*/) {
        return dishMapper.searchDishes(merchantId, keyword);
//        List<Dish> dishes = dishMapper.searchDishes(merchantId, keyword);
//        model.addAttribute("dishes", dishes);
//        return "dish_search_result";
    }
//
//    @GetMapping("/dishDetails")
//    public Dish getDishDetails(@RequestParam Long merchantId, @RequestParam Long id, Model model) {
//        return dishMapper.getDishDetails(merchantId, id);
////        Dish dish = dishMapper.getDishByID(id);
////        model.addAttribute("dish", dish);
////        return "dish_details";
//    }

//    @GetMapping("/searchPage")
//    public String searchPage() {
//        return "user_search";
//    }


    // 删除用户
//    @RequestMapping("/delete/{id}")
//    public String delete(@PathVariable Long id) {
//        if (userMapper.findByID(id) != null){
//            userMapper.delete(id);
//            return "User" + id + " is deleted successfully!";
//        }else{
//            return "User" + id + " does not exist!";
//        }
//    }

}
