package com.example.pj.mapper;

import com.example.pj.entity.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {
    @Select("SELECT * FROM dish WHERE merchantId = #{merchantId} AND dishName LIKE CONCAT('%', #{keyword}, '%')")
    List<Dish> searchDishes(@Param("merchantId") Long merchantId, @Param("keyword") String keyword);

//    @Select("SELECT * FROM dish WHERE merchantId = #{merchantId} AND dishName LIKE CONCAT('%', #{keyword}, '%')")
//    Dish getDishDetails(@Param("merchantId") Long merchantId, @Param("keyword") String keyword);

    @Select("SELECT * FROM dish WHERE dishId = #{dishId}")
    Dish getDishDetails(@Param("dishId") Long dishId);

    @Select("SELECT DishID, DishName, Description FROM dish WHERE DishID = #{dishId}")
    Dish getDishById(@Param("dishId") Long dishId);

    @Select("SELECT DishID, DishName, Description FROM dish WHERE DishName = #{dishName}")
    Dish getDishByName(@Param("dishId") String dishName);

    @Select("SELECT AVG(rating) AS average_rating FROM Review WHERE dishId = #{dishId}")
    Float getAvgRating(@Param("dishId") Long dishId);

}