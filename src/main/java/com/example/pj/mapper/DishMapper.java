package com.example.pj.mapper;

import com.example.pj.entity.*;
import org.apache.ibatis.annotations.*;

import java.awt.*;
import java.util.List;
import java.util.Map;

@Mapper
public interface DishMapper {
    @Select("SELECT * FROM dish WHERE merchantId = #{merchantId} AND dishName LIKE CONCAT('%', #{keyword}, '%')")
    List<Dish> searchDishes(@Param("merchantId") Long merchantId, @Param("keyword") String keyword);

    @Select("SELECT r.*, d.dishName " +
            "FROM Review r " +
            "JOIN dish d on d.dishId = r.dishId " +
            "WHERE r.dishId = #{dishId}")
    List<Review> dishReview(@Param("dishId") Long dishId);

    @Select("SELECT * FROM dish")
    List<Dish> getAllDishes();

    @Select("SELECT DishID, DishName, Description FROM dish WHERE DishID = #{dishId}")
    Dish getDishById(@Param("dishId") Long dishId);

    @Select("SELECT DishID, DishName, category, picture, Description FROM dish WHERE DishName LIKE CONCAT('%', #{dishName}, '%') and merchantId = #{merchantId}")
    List<Dish> getDishByName(@Param("dishName") String dishName, @Param("merchantId")Long merchantId);

    @Select("SELECT * FROM dish WHERE merchantId = #{merchantId}")
    List<Dish> getDishByMerchantId(Long merchantId);

    @Select("SELECT * FROM dish WHERE dishId = #{dishId}")
    Dish getDishDetails(@Param("dishId") Long dishId);

    @Update("UPDATE Dish SET Category = #{category} WHERE DishID = #{dishId}")
    void updateDishCategory(@Param("dishId") Long dishId, @Param("category") String category);

    @Insert("INSERT INTO Dish (DishID,DishName, Category, Description, Picture, Flavor, Ingredients, Allergens, NutritionInfo, MerchantID) VALUES (#{dishId}, #{dishName}, #{category}, #{description}, #{picture}, #{flavor}, #{ingredients}, #{allergens}, #{nutritionInfo},  #{merchantId})")
    void insertDish(Dish dish);

    @Delete("DELETE FROM Dish WHERE DishID = #{dishId}")
    void deleteDish(@Param("dishId") Long dishId);


    @Select("SELECT AVG(rating) AS average_rating FROM Review WHERE dishId = #{dishId}")
    Float getAvgRating(@Param("dishId") Long dishId);

//    /*
    @Select("SELECT d.DishID, d.DishName, COUNT(fd.dishID) as favoriteCount " +
            "FROM FavoriteDish fd " +
            "JOIN Dish d ON fd.DishID = d.DishID " +
            "WHERE d.MerchantID = #{merchantId} " +
            "GROUP BY d.DishID, d.DishName")
    List<Map<String, Object>> getFavoriteCountsByMerchantId(Long merchantId);
//     */

    // 查询各个菜品通过排队点餐和在线点餐的销量
    @Select("SELECT oi.DishID AS dishId, d.dishName AS DishName, m.MerchantName AS merchantName " +
            ",SUM(CASE WHEN o.OrderType = 'Queue' THEN oi.Quantity ELSE 0 END) AS QueueSales, " +
            "SUM(CASE WHEN o.OrderType = 'Online' THEN oi.Quantity ELSE 0 END) AS OnlineSales " +
            "FROM MyOrder o " +
            "JOIN OrderItem oi ON o.OrderID = oi.OrderID " +
            "JOIN Dish d ON oi.DishID = d.DishID " +
            "LEFT JOIN merchant m on d.merchantId = m.merchantId " +
            "GROUP BY oi.DishID, d.dishName ;")
    List<Sales> getSales();

    @Select("SELECT oi.DishID, o.UserID, COUNT(*) as purchaseCount " +
            "FROM OrderItem oi " +
            "JOIN MyOrder o ON oi.OrderID = o.OrderID " +
            "WHERE oi.DishID = #{dishId} " +
            "GROUP BY o.UserID " +
            "ORDER BY purchaseCount DESC " +
            "LIMIT 1")
    Map<String, Object> getTopBuyerForDish(Long dishId);
}