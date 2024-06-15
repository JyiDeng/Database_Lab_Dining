package com.example.pj.controller;

import com.example.pj.entity.Dish;
import com.example.pj.entity.Merchant;
import com.example.pj.entity.User;
import com.example.pj.entity.MyOrder;
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
//        return merchantMapper.getMerchantDetails(id);
//        Merchant merchant = merchantMapper.getMerchantByID(id);
////        List<Dish> dishes = merchantMapper.getDishesByMerchantID(id);
//        model.addAttribute("merchant", merchant);
////        model.addAttribute("dishes", dishes);
//        return "merchant_details";
    }

    @RequestMapping("/{path}/searchDishes")
    public List<Dish> searchDishes(@RequestParam Long merchantId, @RequestParam String keyword/*, Model model*/,@PathVariable Long path) {
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

}
