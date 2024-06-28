package com.example.pj.controller;

import com.example.pj.entity.*;
import com.example.pj.mapper.DishMapper;
import com.example.pj.mapper.MerchantMapper;
import com.example.pj.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    MerchantMapper merchantMapper;
    @Autowired
    DishMapper dishMapper;

    @GetMapping("/{path}/allUserList")
    public List<User> userList(@PathVariable String path) {
        return userMapper.findAll();
    }

    @RequestMapping("/{path}/addUser")
    public String addUser(@RequestParam Long id, String userName, String gender, Long ecardId, String role, Integer age,String password, @PathVariable String path) {
        if (userMapper.findByID(id) == null){
            User newUser = new User(id,userName,gender,ecardId,role,age,password);
            userMapper.insert(newUser);
            return "User" + id + " is added successfully!";
        }else{
            return "User" + id + " already exists!";
        }
    }

    @RequestMapping("/{path}/updateUser")
    public String updateUser(@RequestParam Long id, String userName, String gender, Long ecardId, String role, Integer age, @PathVariable String path) {
        if (userMapper.findByID(id) != null){
            User currentUser = userMapper.findByID(id);
            currentUser.setUserName(userName);
            currentUser.setGender(gender);
            currentUser.setEcardId(ecardId);
            currentUser.setRole(role);
            currentUser.setAge(age);
            userMapper.update(currentUser);
            return "User" + id + " is updated successfully!";
        }else{
            return "User" + id + " does not exist!";
        }
    }


    @RequestMapping("/{path}/deleteUser/{id}")

    public String deleteUser(@PathVariable Long id, @PathVariable String path) {
        if (userMapper.findByID(id) != null){
            userMapper.delete(id);
            return "User" + id + " is deleted successfully!";
        }else{
            return "User" + id + " does not exist!";
        }
    }

    @GetMapping("/{path}/allMerchantList")
    public List<Merchant> merchantList(@PathVariable String path) {
        return merchantMapper.findAll();
    }

    @RequestMapping("/{path}/addMerchant")
    public String addMerchant(@RequestParam Long id, String merchantName, String mainDishes, String address, @PathVariable String path) {
        if (merchantMapper.getMerchantByID(id) == null){
            Merchant newMerchant = new Merchant(id, merchantName, mainDishes, address);
            merchantMapper.insert(newMerchant);
            return "Merchant" + id + " is added successfully!";
        }else{
            return "Merchant" + id + " already exists!";
        }
    }

    @RequestMapping("/{path}/updateMerchant")
    public String updateMerchant(@RequestParam Long id,  String merchantName, String mainDishes, String address, @PathVariable String path) {
        if (merchantMapper.getMerchantByID(id) != null){
            Merchant currentMerchant = merchantMapper.getMerchantByID(id);
            currentMerchant.setMerchantId(id);
            currentMerchant.setMerchantName(merchantName);
            currentMerchant.setMainDishes(mainDishes);
            currentMerchant.setAddress(address);
            merchantMapper.update(currentMerchant);
            return "Merchant" + id + " is updated successfully!";
        }else{
            return "Merchant" + id + " does not exist!";
        }
    }


    @RequestMapping("/{path}/deleteMerchant/{id}")
    public String deleteMerchant(@PathVariable Long id, @PathVariable String path) {
        if (merchantMapper.getMerchantByID(id) != null){
            merchantMapper.delete(id);
            return "Merchant" + id + " is deleted successfully!";
        }else{
            return "Merchant" + id + " does not exist!";
        }
    }


    @RequestMapping("/{path}/user-characteristics/age")
    public List<UserCharacteristic> getCharacteristicsByAge(@PathVariable Long path) {
//        List<UserCharacteristic> characteristics = userMapper.getCharacteristicsByAge();
//        return ResponseEntity.ok(characteristics);
        return userMapper.getCharacteristicsByAge();
    }

    @GetMapping("/{path}/user-characteristics/gender")
    public List<UserCharacteristic> getCharacteristicsByGender(@PathVariable Long path) {
//        List<UserCharacteristic> characteristics = userMapper.getCharacteristicsByGender();
//        return ResponseEntity.ok(characteristics);
        return userMapper.getCharacteristicsByGender();
    }

    @GetMapping("/{path}/user-characteristics/role")
    public List<UserCharacteristic> getCharacteristicsByRole(@PathVariable Long path) {
//        List<UserCharacteristic> characteristics = userMapper.getCharacteristicsByRole();
//        return ResponseEntity.ok(characteristics);
        return userMapper.getCharacteristicsByRole();
    }

    @GetMapping("/{path}/user-characteristics/reviews")
    public List<UserReviewCharacteristic>  getReviewCharacteristics(@PathVariable Long path) {
//        List<UserReviewCharacteristic> characteristics = userMapper.getReviewCharacteristics();
//        return ResponseEntity.ok(characteristics);
        return userMapper.getReviewCharacteristics();
    }

    @RequestMapping("/{path}/user-activity/timeOfDay")
    public List<UserActivity> activityTimeOfDay(@RequestParam Long userId, @PathVariable Long path) {
        return userMapper.getActivityByTimeOfDay(path);
    }

    @GetMapping("/{path}/dish/{dishId}/top-buyer")
    public Map<String, Object> getTopBuyerForDish(@PathVariable Long dishId, @PathVariable Long path) {
        return dishMapper.getTopBuyerForDish(dishId);
    }
    @RequestMapping("/{path}/sales")
    public List<Sales> getSales(Model model, @PathVariable String path, @RequestParam Long merchantId) {
        return dishMapper.getSales(merchantId);

    }
}
