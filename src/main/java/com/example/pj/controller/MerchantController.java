package com.example.pj.controller;

import com.example.pj.entity.Merchant;
import com.example.pj.entity.User;
import com.example.pj.entity.Dish;
import com.example.pj.mapper.UserMapper;
import com.example.pj.mapper.DishMapper;
import com.example.pj.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/merchant")
public class MerchantController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    DishMapper dishMapper;

    @RequestMapping("/{path}/selfInfo")
    public User getMerchantSelfInfo(@PathVariable Long path) {
        return userMapper.findByID(path);
    }

//    @Autowired
//    private MerchantService merchantService;
//
//    @GetMapping("/search")
//    public List<Merchant> searchMerchant(@RequestParam String keyword) {
//        return merchantService.searchMerchant(keyword);
//    }

//    @GetMapping({"", "/", "index"})
//    public String index() {
//        return "欢迎来到商家索引页！<br>以下为操作示例：<br>-------------------<br>" +
//                "TODO" +
//                "<br>-------------------<br>" +
//                "用户索引页：`http://localhost:8080/user/`<br>" +
//                "管理员索引页：`http://localhost:8080/admin/`<br>";
//
//    }
    // 更新菜品分类
    @PutMapping("/dish/{dishId}/category")
    public void updateDishCategory(@PathVariable Long dishId, @RequestParam String category) {
        dishMapper.updateDishCategory(dishId, category);
    }

    // 添加新菜品
    @PostMapping("/dish")
    public void addDish(@RequestBody Dish dish) {
        dishMapper.addDish(dish);
    }

    // 删除菜品
    @DeleteMapping("/dish/{dishId}")
    public void deleteDish(@PathVariable Long dishId) {
        dishMapper.deleteDish(dishId);
    }
}
