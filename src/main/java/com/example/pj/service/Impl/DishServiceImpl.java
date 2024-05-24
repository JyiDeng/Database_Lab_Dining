package com.example.pj.service.Impl;

import com.example.pj.entity.Dish;
import com.example.pj.mapper.DishMapper;
import com.example.pj.service.DishService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;


    @Override
    public List<Dish> searchDishes(Long merchantId, String keyword) {
        return dishMapper.searchDishes(merchantId, keyword);
    }

//    @Override
//    public Dish getDishByID(Long id) {
//        return dishMapper.getDishDetails(id);
//    }
}