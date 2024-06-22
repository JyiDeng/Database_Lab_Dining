package com.example.pj.controller;

import com.example.pj.entity.*;
import com.example.pj.mapper.*;
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
    UserMapper userMapper;

    @Autowired
    MerchantMapper merchantMapper;

    @Autowired
    DishMapper dishMapper;
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    OrderMapper orderMapper;


//    @RequestMapping("/id={id}")
//    public User getUserById(@PathVariable Long id) {
//        return userMapper.findByID(id);
//    }

    // 显示特定用户个人信息
    @RequestMapping("/{path}/userSelfInfo")
    public String getUserSelfInfo(@PathVariable Long path,Model model) {
        User user = userMapper.findByID(path);
        model.addAttribute(user);
        return "userSelfInfo";
    }

    @RequestMapping("/{path}/searchMerchant2")
    public String searchMerchant2(@RequestParam String keyword,@PathVariable Long path, Model model) {
        List<Merchant> merchants = merchantMapper.searchMerchant(keyword);
        model.addAttribute("merchants", merchants);
        return "searchMerchant"; // 返回模板文件名

    }

    @RequestMapping("/{path}/searchMerchantDetails")
    public String getMerchantDetails(@RequestParam Long id, Model model,@PathVariable Long path) {
        Merchant merchant = merchantMapper.getMerchantDetails(id);
        model.addAttribute("merchant",merchant);
        return "merchantDetail";
    }

    @RequestMapping("/{path}/searchDishes")
    public String searchDishes(@RequestParam Long merchantId, String keyword, Model model,@PathVariable Long path) {
        List<Dish> dishes = dishMapper.getDishByName(keyword,merchantId);
        model.addAttribute("dishes", dishes);
        return "dishSearch";
    }

    @RequestMapping("/{path}/searchDishDetails")
    public String getDishDetails(@RequestParam Long dishId, Model model,@PathVariable Long path) {
        Dish dish = dishMapper.getDishDetails(dishId);
        model.addAttribute("dish",dish);
        return "dishDetail";
    }

    // 收藏菜品
    @RequestMapping("/{userId}/favoriteDish")
    public void favoriteDish(@PathVariable Long userId, @RequestParam Long dishId) {
        userMapper.favoriteDish(userId, dishId);
//        return "菜品"+ dishId +"已收藏!";
    }

    // 收藏商户
    @RequestMapping("/{userId}/favoriteMerchant")
    public void favoriteMerchant(@PathVariable Long userId, @RequestParam Long merchantId) {
        userMapper.favoriteMerchant(userId, merchantId);
//        return "商户"+ merchantId +"已收藏!";
    }

    // 查询用户订单
    @RequestMapping("/{userId}/viewOrders")
    public String getOrdersByUserId(@PathVariable Long userId,Model model) {
        List<MyOrder> orders = userMapper.findOrdersByUserId(userId);
        model.addAttribute("orders",orders);
        return "orderView";
    }

    @RequestMapping("/{path}/orderDetail")
    public String orderDetail(@PathVariable String path, Model model, @RequestParam Long orderId) {
        List<OrderItem> orderItems = orderMapper.getOrderItemsByOrderId(path,orderId);
        model.addAttribute("orderItems", orderItems);

        return "orderDetail";
    }

    @RequestMapping("/{path}/review/{dishId}")
    public String getDishReview(@PathVariable Long dishId,Model model,@PathVariable Long path){
        List<Review> reviews = userMapper.dishReview(dishId);
        model.addAttribute("reviews", reviews);
        Float rating = dishMapper.getAvgRating(dishId);
        model.addAttribute("rating", rating);
        return "reviewDish"; // 返回模板文件名
    }

    // 查询菜品的最新价格
    @RequestMapping("{path}/latestPrice/{menuItemId}")
    public MenuPrice getLatestPriceByMenuItemId(@PathVariable Long menuItemId,@PathVariable Long path) {
        return menuMapper.findLatestPriceByMenuItemId(menuItemId);
    }


    @RequestMapping("/{path}/allMenus")
    public String getMenus(@PathVariable Long path, Model model) {
        List<Menu> menus = menuMapper.findAllMenus(path);
        model.addAttribute("menus",menus);
        return "menuIntro";
    }
    @RequestMapping("/{path}/menuItems")
    public String enterMenu(@PathVariable Long path, Model model,@RequestParam Long id) {
        List<MenuItem> menuItems = menuMapper.getMenuItemsByMerchantId(id);
        model.addAttribute("menuItems",menuItems);

        Long pendingCount = orderMapper.countPendingOrders(id);
        model.addAttribute("hasPendingOrder", pendingCount > 0);
        return "menuItems";
    }

    public void addOrderItem2Order(){

    }

}
