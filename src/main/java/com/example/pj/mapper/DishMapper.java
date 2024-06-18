package com.example.pj.mapper;

import com.example.pj.entity.*;
import com.example.pj.entity.Menu;
import com.example.pj.entity.MenuItem;
import org.apache.ibatis.annotations.*;

import java.awt.*;
import java.util.List;

@Mapper
public interface DishMapper {
    @Select("SELECT * FROM dish WHERE merchantId = #{merchantId} AND dishName LIKE CONCAT('%', #{keyword}, '%')")
    List<Dish> searchDishes(@Param("merchantId") Long merchantId, @Param("keyword") String keyword);

    @Select("SELECT * FROM dish WHERE dishId = #{dishId}")
    List<Dish> findById(@Param("dishId") Long dishId);

//    @Select("SELECT * FROM dish WHERE merchantId = #{merchantId} AND dishName LIKE CONCAT('%', #{keyword}, '%')")
//    Dish getDishDetails(@Param("merchantId") Long merchantId, @Param("keyword") String keyword);

    @Update("UPDATE Dish SET Category = #{category} WHERE DishID = #{dishId}")
    void updateDishCategory(@Param("dishId") Long dishId, @Param("category") String category);

    @Insert("INSERT INTO Dish (DishID,DishName, Category, Description, Picture, Flavor, Ingredients, Allergens, NutritionInfo, AvgRating, MerchantID) VALUES (#{dishId}, #{dishName}, #{category}, #{description}, #{picture}, #{flavor}, #{ingredients}, #{allergens}, #{nutritionInfo}, #{avgRating}, #{merchantId})")
    void insertDish(Dish dish);

    @Delete("DELETE FROM Dish WHERE DishID = #{dishId}")
    void deleteDish(@Param("dishId") Long dishId);


    @Select("SELECT * FROM dish WHERE dishId = #{dishId}")
    Dish getDishDetails(@Param("dishId") Long dishId);

    @Select("SELECT DishID, DishName, Description FROM dish WHERE DishID = #{dishId}")
    Dish getDishById(@Param("dishId") Long dishId);

    @Select("SELECT DishID, DishName, Description FROM dish WHERE DishName = #{dishName}")
    Dish getDishByName(@Param("dishId") String dishName);

    @Select("SELECT AVG(rating) AS average_rating FROM Review WHERE dishId = #{dishId}")
    Float getAvgRating(@Param("dishId") Long dishId);

}