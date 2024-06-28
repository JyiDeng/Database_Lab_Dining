package com.example.pj.controller;

import com.example.pj.entity.*;
import com.example.pj.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

//@RestController
// 如果用Controller才能用html加载，如果用RestController才能用文字加载
@RestController
@RequestMapping("/userAdvanced")
public class AdvancedUserController {

    @Autowired
    UserMapper userMapper;
//    @RequestMapping("/{path}/user-characteristics/age")
//    public List<UserCharacteristic> getCharacteristicsByAge(@PathVariable Long path) {
////        List<UserCharacteristic> characteristics = userMapper.getCharacteristicsByAge();
////        return ResponseEntity.ok(characteristics);
//        return userMapper.getCharacteristicsByAge();
//    }
//
//    @GetMapping("/{path}/user-characteristics/gender")
//    public List<UserCharacteristic> getCharacteristicsByGender(@PathVariable Long path) {
////        List<UserCharacteristic> characteristics = userMapper.getCharacteristicsByGender();
////        return ResponseEntity.ok(characteristics);
//        return userMapper.getCharacteristicsByGender();
//    }
//
//    @GetMapping("/{path}/user-characteristics/role")
//    public List<UserCharacteristic> getCharacteristicsByRole(@PathVariable Long path) {
////        List<UserCharacteristic> characteristics = userMapper.getCharacteristicsByRole();
////        return ResponseEntity.ok(characteristics);
//        return userMapper.getCharacteristicsByRole();
//    }
//
//    @GetMapping("/{path}/user-characteristics/reviews")
//    public List<UserReviewCharacteristic>  getReviewCharacteristics(@PathVariable Long path) {
////        List<UserReviewCharacteristic> characteristics = userMapper.getReviewCharacteristics();
////        return ResponseEntity.ok(characteristics);
//        return userMapper.getReviewCharacteristics();
//    }

    @Autowired
    FavoriteMapper favoriteMapper;

    @GetMapping("/{path}/favoriteSales")
    public List<DishSales> getFavoriteDishSales(@PathVariable Long path,
            @RequestParam Long userId,
            @RequestParam String timePeriod) {
        return favoriteMapper.getFavoriteDishSales(userId, timePeriod);
//        return ResponseEntity.ok(sales);
    }

    // 用户活跃度分析
    @GetMapping("/{path}/user-activity/weekly")
    public List<UserActivity> getWeeklyActivity(@PathVariable Long path, @RequestParam int timePeriod) {
        return userMapper.getWeeklyActivity(path);
//        return ResponseEntity.ok(activity);
    }

    @GetMapping("/{path}/user-activity/monthly")
    public List<UserActivity>getMonthlyActivity(@PathVariable Long path, @RequestParam int timePeriod) {
        return userMapper.getMonthlyActivity(path);
//        return ResponseEntity.ok(activity);
    }

    @GetMapping("/{path}/user-activity/yearly")
    public List<UserActivity>getYearlyActivity(@PathVariable Long path, @RequestParam int timePeriod) {
        return userMapper.getYearlyActivity(path);
//        return ResponseEntity.ok(activity);
    }

//    @GetMapping("/{path}/user-activity/timeOfDay")
//    public List<UserActivity> getActivityByTimeOfDay(@PathVariable Long path) {
//        return userMapper.getActivityByTimeOfDay(path);
////        return ResponseEntity.ok(activity);
//    }

    @GetMapping("/{path}/loyal-customers-distribution")
    public List<PurchaseDistribution> getLoyalCustomerDistribution(@PathVariable Long path,
            @RequestParam Long merchantId,
            @RequestParam String timePeriod,
            @RequestParam Long threshold) {
        return userMapper.getLoyalCustomerDistribution(merchantId, timePeriod, threshold);
//        return ResponseEntity.ok(distribution);
    }

}
