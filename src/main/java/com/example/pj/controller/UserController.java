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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
        return "Welcome to **User** Page!\nEnter in the url \"allUserList\" to see all users and \"id=k\" to see the kth user.";
    }

    @GetMapping({"/allUserList"})
    public List<User> userList() {
        return userMapper.findAll();
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

    @GetMapping("/searchMerchantDetails")
    public Merchant getMerchantDetails(@RequestParam Long id, Model model) {
        return merchantMapper.getMerchantByID(id);
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
    @DeleteMapping("/delete/{id}")
    // There was an unexpected error (type=Method Not Allowed, status=405). Method 'GET' is not supported.
    public String delete(@PathVariable Long id) {
        userMapper.delete(id);
        return "User" + id + " deleted successfully!";
    }

//    @PostMapping("/hello")
//    @ResponseBody
//    public String hello(Model model){
//        model.addAttribute("str","hello!");
//        return "hello";
//    }
}
