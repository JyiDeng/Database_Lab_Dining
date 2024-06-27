package com.example.pj.controller;

import com.example.pj.entity.Merchant;
import com.example.pj.entity.User;
import com.example.pj.mapper.MerchantMapper;
import com.example.pj.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    MerchantMapper merchantMapper;


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
    public String addMerchant(@RequestParam Long id, Long merchantId, String merchantName, String mainDishes, String address,String menuId, @PathVariable String path) {
        if (merchantMapper.getMerchantByID(id) == null){
            Merchant newMerchant = new Merchant(merchantId, merchantName, mainDishes, address, menuId);
            merchantMapper.insert(newMerchant);
            return "Merchant" + id + " is added successfully!";
        }else{
            return "Merchant" + id + " already exists!";
        }
    }

    @RequestMapping("/{path}/updateMerchant")
    public String updateMerchant(@RequestParam Long id, Long merchantId, String merchantName, String mainDishes, String address,String menuId, @PathVariable String path) {
        if (merchantMapper.getMerchantByID(id) != null){
            Merchant currentMerchant = merchantMapper.getMerchantByID(id);
            currentMerchant.setMerchantId(merchantId);
            currentMerchant.setMerchantName(merchantName);
            currentMerchant.setMainDishes(mainDishes);
            currentMerchant.setAddress(address);
            currentMerchant.setMenuId(menuId);
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
}
