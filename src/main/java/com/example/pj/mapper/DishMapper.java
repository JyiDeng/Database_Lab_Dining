package com.example.pj.mapper;

import com.example.pj.entity.*;
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

    @Select("SELECT * FROM menu WHERE menuID = #{menuId}")
    Menu findMenuById(Long menuId);

    @Insert("INSERT INTO menu (merchantID) VALUES (#{merchantId})")
    @Options(useGeneratedKeys = true, keyProperty = "menuId")
    void insert(Menu menu);

    @Delete("DELETE FROM menu WHERE menuID = #{menuId}")
    void deleteMenu(Long menuId);

    @Select("SELECT * FROM menuItem WHERE menuItemID = #{menuItemId}")
    MenuItem findMenuItemById(Long menuItemId);

    @Select("SELECT * FROM menuItem WHERE menuID = #{menuId}")
    List<MenuItem> findByMenuId(Long menuId);

    @Insert("INSERT INTO menuItem (menuID, dishID, price) VALUES (#{menuId}, #{dishId}, #{price})")
    @Options(useGeneratedKeys = true, keyProperty = "menuItemId")
    void insertMenuItem(MenuItem menuItem);

    @Update("UPDATE menuItem SET price = #{price} WHERE menuItemID = #{menuItemId}")
    void updatePrice(@Param("menuItemId") Long menuItemId, @Param("price") Float price);

    @Delete("DELETE FROM menuItem WHERE menuItemID = #{menuItemId}")
    void delete(Long menuItemId);

    @Select("SELECT * FROM menuPrice WHERE menuPriceID = #{menuPriceId}")
    MenuPrice findmenuPriceById(Long menuPriceId);

    @Select("SELECT * FROM menuPrice WHERE menuItemID = #{menuItemId} ORDER BY effectiveDate DESC LIMIT 1")
    MenuPrice findLatestByMenuItemId(Long menuItemId);

    @Insert("INSERT INTO menuPrice (menuItemID, price, effectiveDate, endDate) VALUES (#{menuItemId}, #{price}, #{effectiveDate}, #{endDate})")
    @Options(useGeneratedKeys = true, keyProperty = "menuPriceId")
    void insertMenuPrice(MenuPrice menuPrice);

    @Select("SELECT * FROM menuPrice WHERE menuItemID = #{menuItemID} AND endDate IS NULL")
    MenuPrice findLatestPriceByMenuItemID(Long menuItemID);

    @Update("UPDATE menuPrice SET endDate = #{endDate} WHERE menuItemID = #{menuItemID} AND endDate IS NULL")
    void updateMenuPriceEndDate(@Param("menuItemID") Long menuItemID, @Param("endDate") String endDate);


}