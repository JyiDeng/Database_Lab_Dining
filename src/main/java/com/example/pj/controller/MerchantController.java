package com.example.pj.controller;

import com.example.pj.entity.Merchant;
import com.example.pj.entity.User;
import com.example.pj.entity.Dish;
import com.example.pj.entity.Menu;
import com.example.pj.entity.MenuItem;
import com.example.pj.entity.MenuPrice;
import com.example.pj.mapper.UserMapper;
import com.example.pj.mapper.DishMapper;
import com.example.pj.service.MerchantService;
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

    // 新建菜单
    @PostMapping("/create")
    public Menu createMenu(@RequestBody Menu menu) {
        dishMapper.insert(menu);
        return menu;
    }

    // 新增菜品到菜单
    @PostMapping("/item/add")
    public MenuItem addMenuItem(@RequestBody MenuItem menuItem) {
        dishMapper.insertMenuItem(menuItem);
        return menuItem;
    }

    // 删除菜单中的菜品
    @DeleteMapping("/item/delete/{menuItemId}")
    public String deleteMenuItem(@PathVariable Long menuItemId) {
        dishMapper.delete(menuItemId);
        return "MenuItem deleted successfully";
    }

    // 修改菜品价格
    @PutMapping("/item/updatePrice/{menuItemId}")
    public String updateMenuItemPrice(@PathVariable Long menuItemId, @RequestParam Float newPrice) {
        // 获取当前时间作为生效日期
        LocalDateTime now = LocalDateTime.now();

        // 查找最新的价格记录
        MenuPrice latestMenuPrice = dishMapper.findLatestByMenuItemId(menuItemId);
        if (latestMenuPrice != null) {
            // 更新结束时间
            latestMenuPrice.setEndDate(now);
            dishMapper.insertMenuPrice(latestMenuPrice);
        }

        // 插入新的价格记录
        MenuPrice menuPrice = new MenuPrice(null, menuItemId, newPrice, now, null);
        dishMapper.insertMenuPrice(menuPrice);

        // 更新 menuItem 表中的价格
        dishMapper.updatePrice(menuItemId, newPrice);

        return "Price updated successfully";
    }

    // 查询菜单
    @GetMapping("/items/{menuId}")
    public List<MenuItem> getMenuItems(@PathVariable Long menuId) {
        return dishMapper.findByMenuId(menuId);
    }

    // 查询菜品的最新价格
    @GetMapping("/menuItem/{menuItemID}/price")
    public MenuPrice getLatestPriceByMenuItemID(@PathVariable Long menuItemID) {
        return dishMapper.findLatestPriceByMenuItemID(menuItemID);
    }

}
