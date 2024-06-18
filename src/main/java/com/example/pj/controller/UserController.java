package com.example.pj.controller;

import com.example.pj.entity.*;
import com.example.pj.mapper.DishMapper;
import com.example.pj.mapper.MerchantMapper;
import com.example.pj.mapper.UserMapper;
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


    // 显示特定用户
    @RequestMapping("/id={id}")
    public User getUserByID(@PathVariable Long id) {
        return userMapper.findByID(id);
    }

    @RequestMapping("/{path}/selfInfo")
    public User getUserSelfInfo(@PathVariable Long path) {
        return userMapper.findByID(path);
    }

    @RequestMapping("/{path}/searchMerchant")
    public List<Merchant> searchMerchant(@RequestParam String keyword,@PathVariable Long path) {
        return merchantMapper.searchMerchant(keyword);
    }

    @RequestMapping("/{path}/searchMerchant2")
    public String searchMerchant2(@RequestParam String keyword,@PathVariable Long path, Model model) {
        List<Merchant> merchants = merchantMapper.searchMerchant(keyword);
//        model.addAttribute("merchantId", merchants);
        model.addAttribute("merchants", merchants);
        return "searchMerchant"; // 返回模板文件名
//        return "user_search";
    }

    @RequestMapping("/{path}/searchMerchantDetails")
    public String getMerchantDetails(@RequestParam Long id, Model model,@PathVariable Long path) {
        Merchant merchant = merchantMapper.getMerchantDetails(id);
        model.addAttribute("merchant",merchant);
        return "merchantDetail";
    }

    @RequestMapping("/{path}/searchDishes")
    public String searchDishes(@RequestParam String dishName, @RequestParam String keyword, Model model,@PathVariable Long path) {
        Dish dish = dishMapper.getDishByName(dishName);
        model.addAttribute("dish", dish);
        return "searchDish";
    }

    @RequestMapping("/{path}/searchDishDetails")
    public String getDishDetails(@RequestParam Long dishId, Model model,@PathVariable Long path) {
        Dish dish = dishMapper.getDishDetails(dishId);
        model.addAttribute("dish",dish);
        return "dishDetail";
    }

    // 收藏菜品
    @PostMapping("/{userId}/favoriteDish")
    public String favoriteDish(@PathVariable Long userId, @RequestParam Long dishId) {
        userMapper.favoriteDish(userId, dishId);
        return "菜品已收藏";
    }

    // 收藏商户
    @PostMapping("/{userId}/favoriteMerchant")
    public String favoriteMerchant(@PathVariable Long userId, @RequestParam Long merchantId) {
        userMapper.favoriteMerchant(userId, merchantId);
        return "商户已收藏";
    }

    // 查询用户订单
    @GetMapping("/{userId}/orders")
    public List<MyOrder> getOrdersByUserId(@PathVariable Long userId) {
        return userMapper.findOrdersByUserId(userId);
    }

    @GetMapping("/review/{dishId}")
    public String getDishReview(@PathVariable Long dishId,Model model){
        List<Review> reviews = userMapper.dishReview(dishId);
        model.addAttribute("reviews", reviews);
        Float rating = dishMapper.getAvgRating(dishId);
        model.addAttribute("rating", rating);
        return "dishReview"; // 返回模板文件名
    }

}
