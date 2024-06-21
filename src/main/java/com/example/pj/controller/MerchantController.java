package com.example.pj.controller;

import com.example.pj.entity.*;
import com.example.pj.mapper.MenuMapper;
import com.example.pj.mapper.MerchantMapper;
import com.example.pj.mapper.UserMapper;
import com.example.pj.mapper.DishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/merchant")
public class MerchantController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    DishMapper dishMapper;
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    MerchantMapper merchantMapper;

    @RequestMapping("/{path}/merchantSelfInfo")
    public Merchant getMerchantSelfInfo(@PathVariable Long path) {
        return merchantMapper.getMerchantByID(path);
    }

    // 更新菜品分类
    @RequestMapping("/{path}/updateDishCategory/{dishId}")
    public String updateDishCategory(@PathVariable Long path, @PathVariable Long dishId, @RequestParam String category) {

        if (dishMapper.getDishById(dishId) != null) {
            dishMapper.updateDishCategory(dishId, category);
            return "Dish" + dishId + " updated!";
        }else{
            return "Dish" + dishId + " does not exist!";
        }
    }

    // 查看所有菜品
    @RequestMapping("/{path}/allDishes")
    public List<Dish> viewAllDishes(@PathVariable Long path){
        return dishMapper.getAllDishes();
    }
    // 添加新菜品
    @RequestMapping("/{path}/addDish")
    public String addDish(@PathVariable Long path, @RequestParam Long dishId, String dishName, String category, String description, String picture, String flavor, String ingredients, String allergens, String nutritionInfo, Long merchantId) {
        if (dishMapper.getDishById(dishId) == null) {
            Dish newDish = new Dish(dishId, dishName, category, description, picture, flavor, ingredients, allergens, nutritionInfo, merchantId);
            dishMapper.insertDish(newDish);
            return "Dish " + dishId + " is added successfully!";
        } else {
            return "Dish " + dishId + " already exists!";
        }
    }

    // 删除菜品
    @RequestMapping("/{path}/deleteDish")
    public String deleteDish(@PathVariable Long path,@RequestParam Long dishId) {
        if (dishMapper.getDishById(dishId) != null) {
            dishMapper.deleteDish(dishId);
            return "Dish " + dishId + " deleted!";
        }else{
            return "Dish " + dishId + " does not exist!";
        }

    }

    // 新建菜单
    @RequestMapping("/{path}/createMenu")
//    RequestParam似乎会去找哪个html，但是PathVariable会正常返回字符串
    public String createMenu(@PathVariable Long path,@RequestParam Long menuId) {
        if (menuMapper.findMenuById(menuId) == null) {
            menuMapper.insertMenu(menuId, path);
            return "Menu " + menuId + " created!";
        } else {
            return "Menu " + menuId + " already exists!";
        }
    }


    // 新增菜品到菜单
//    @PostMapping("/item/add")
//    public MenuItem addMenuItem(@RequestBody MenuItem menuItem) {
//        menuMapper.insertMenuItem(menuItem);
//        return menuItem;
//    }
    @RequestMapping("/{path}/addMenuItem")
    public String addMenuItem(@PathVariable Long path, @RequestParam Long menuId, Long dishId,Long menuItemId,Float price) {
        if(menuMapper.findMenuById(menuId) == null){
            return "Menu " + menuId + "does not exist!";
        }

        if (menuMapper.findMenuItemById(menuItemId) == null) {
            MenuItem menuItem = new MenuItem(menuItemId,menuId,dishId,price,null);  //测试dishName，后续查询了可以显示dishName！
            menuMapper.insertMenuItem(menuItem);
            return "MenuItem " + menuItemId + " is added successfully!";
        }else{
            return "MenuItem " + menuItemId + " already exists!";
        }

    }

//    // 查询菜单
//    @RequestMapping("/{path}/allMenus")
//    public List<Menu> getMenus(@PathVariable Long path) {
//        return menuMapper.findAllMenus(path);
//    }





    // 删除菜单中的菜品
    @RequestMapping("/{path}/deleteMenuItem/{menuItemId}")
    public String deleteMenuItem(@PathVariable Long path, @PathVariable Long menuItemId) {
    // 注意外键，需要先删除MenuPrice
        if (menuMapper.findMenuItemById(menuItemId) != null ) {
            menuMapper.deleteMenuPrice(menuItemId);
            menuMapper.deleteMenuItemId(menuItemId);
            return "MenuItem " + menuItemId + " deleted!";
        }else{
            return "MenuItem " + menuItemId + " does not exist!";
        }

    }



    // 修改菜品价格
    @RequestMapping("/{path}/updateMenuItemPrice/{menuItemId}")
    public String updateMenuItemPrice(@PathVariable Long path, @PathVariable Long menuItemId, @RequestParam Float newPrice) {
        if(menuMapper.findMenuItemById(menuItemId) == null){
            return "Menu " + menuItemId + " does not exist!";
        }

        // TODO 增加报错判断
        // 获取当前时间作为生效日期
        LocalDateTime now = LocalDateTime.now();

        // 查找最新的价格记录
        MenuPrice latestMenuPrice = menuMapper.findLatestByMenuItemId(menuItemId);
        if (latestMenuPrice != null) {
            // 更新结束时间
            latestMenuPrice.setEndDate(now);
            menuMapper.insertMenuPrice(latestMenuPrice);
        }

        // 插入新的价格记录
        MenuPrice menuPrice = new MenuPrice(null, menuItemId, newPrice, now, null);
        menuMapper.insertMenuPrice(menuPrice);

        // 更新 menuItem 表中的价格
        menuMapper.updatePrice(menuItemId, newPrice);

        return "Price updated successfully!";
    }




}
