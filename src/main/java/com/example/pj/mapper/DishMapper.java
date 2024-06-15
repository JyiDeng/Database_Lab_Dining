package com.example.pj.mapper;

import com.example.pj.entity.Dish;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DishMapper {
    @Select("SELECT * FROM dish WHERE merchantId = #{merchantId} AND dishName LIKE CONCAT('%', #{keyword}, '%')")
    List<Dish> searchDishes(@Param("merchantId") Long merchantId, @Param("keyword") String keyword);

//    @Select("SELECT * FROM dish WHERE merchantId = #{merchantId} AND dishName LIKE CONCAT('%', #{keyword}, '%')")
//    Dish getDishDetails(@Param("merchantId") Long merchantId, @Param("keyword") String keyword);

    @Update("UPDATE Dish SET Category = #{category} WHERE DishID = #{dishId}")
    void updateDishCategory(@Param("dishId") Long dishId, @Param("category") String category);

    @Insert("INSERT INTO Dish (DishName, Category, Description, Picture, Flavor, Ingredients, Allergens, NutritionInfo, AvgRating, MerchantID) VALUES (#{dishName}, #{category}, #{description}, #{picture}, #{flavor}, #{ingredients}, #{allergens}, #{nutritionInfo}, #{avgRating}, #{merchantId})")
    void addDish(Dish dish);

    @Delete("DELETE FROM Dish WHERE DishID = #{dishId}")
    void deleteDish(@Param("dishId") Long dishId);
}