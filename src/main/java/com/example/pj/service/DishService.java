package com.example.pj.service;

import com.example.pj.entity.Dish;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DishService {

    public List<Dish> searchDishes(Long merchantId, String keyword);

    public Dish getDishById(Long id);
}
