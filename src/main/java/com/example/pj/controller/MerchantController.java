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
import java.util.Map;

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
    // TODO 新的外键出现，需要同样排除 FOREIGN KEY (dishId)
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
    @RequestMapping("/{path}/addMenuItem")
    public String addMenuItem(@PathVariable Long path, @RequestParam Long dishId,Long menuItemId,Float price) {
//        if(menuMapper.findMenuById(path) == null){
//            return "Menu " + path + "does not exist!";
//        }

        if (menuMapper.findMenuItemById(menuItemId) == null) {
            MenuItem menuItem = new MenuItem(menuItemId,dishId,null,null);  //测试dishName，后续查询了可以显示dishName！
            menuMapper.insertMenuItem(menuItem);
            return "MenuItem " + menuItemId + " is added successfully!";
        }else{
            return "MenuItem " + menuItemId + " already exists!";
        }

    }


    // 删除菜单中的菜品
    @RequestMapping("/{path}/deleteMenuItem/{menuItemId}")
    public String deleteMenuItem(@PathVariable Long path, @PathVariable Long menuItemId) {
        // TODO 新的外键出现，需要同样排除 4个FOREIGN KEY (menuItemId)
        if (menuMapper.findMenuItemById(menuItemId) != null ) {
            // menuMapper.deleteMenuPrice(menuItemId);
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
            return "MenuItem " + menuItemId + " does not exist!";
        }

        // 获取当前时间作为生效日期
        LocalDateTime now = LocalDateTime.now();

        // 查找最新的价格记录
//        MenuPrice latestMenuPrice = menuMapper.findLatestByMenuItemId(menuItemId);

        // 更新 menuItem 表中的价格
        menuMapper.updatePrice(menuItemId, newPrice,now);

        return "Price updated successfully!";
    }

//    @RequestMapping("/{path}/allMenuItems")
//    public List<MenuItem> getMenus(@PathVariable Long path, Model model) {
//        return  menuMapper.findAllMenuItems(path);
////        model.addAttribute("menuItems",menuItems);
////        return "menuIntro";
//    }
//     查询所有菜品的收藏量
    @GetMapping("/{merchantId}/favoriteCounts")
    public List<Map<String, Object>> getFavoriteCounts(@PathVariable Long merchantId) {
        return dishMapper.getFavoriteCountsByMerchantId(merchantId);
    }

}
