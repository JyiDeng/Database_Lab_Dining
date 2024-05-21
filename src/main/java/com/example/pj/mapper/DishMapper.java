package com.example.pj.mapper;

import com.example.pj.entity.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface DishMapper {
    List<Dish> searchDishes(@Param("merchantId") Long merchantId, @Param("keyword") String keyword);
    Dish getDishById(@Param("id") Long id);
}