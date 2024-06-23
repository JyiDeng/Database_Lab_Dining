package com.example.pj.mapper;

import com.example.pj.entity.UserFavoriteDish;
import com.example.pj.entity.UserFavoriteMerchant;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FavoriteMapper {


    // 收藏菜品
    @Select("SELECT fd.*, d.dishName as dishName " +
            "FROM FavoriteDish fd " +
            "JOIN dish d ON fd.dishId = d.dishId " +
            "WHERE fd.userId = #{userId} ")
    List<UserFavoriteDish> findAllFavoriteDish(@Param("userId") Long userId);

    @Insert("INSERT INTO FavoriteDish (userId, dishID, FavoriteDate) " +
            "VALUES (#{userId}, #{dishId}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "favoriteDishId")
    void addFavoriteDish(@Param("userId") Long userId, @Param("dishId") Long dishId);

    @Select("SELECT COUNT(*) FROM FavoriteDish " +
            "WHERE userId = #{userId} AND dishId = #{dishId}")
    int findFavoriteDish(@Param("userId") Long userId, @Param("dishId") Long dishId);

    // 收藏商户
    @Select("SELECT fm.*, m.merchantName as merchantName " +
            "FROM FavoriteMerchant fm " +
            "JOIN Merchant m ON fm.merchantId = m.merchantId " +
            "WHERE fm.userId = #{userId} ")
    List<UserFavoriteMerchant> findAllFavoriteMerchant(@Param("userId") Long userId);

    @Select("SELECT COUNT(*) FROM FavoriteMerchant WHERE userId = #{userId} AND merchantId = #{merchantId}")
    int findFavoriteMerchant(@Param("userId") Long userId, @Param("merchantId") Long merchantId);


    @Insert("INSERT INTO FavoriteMerchant (userId, merchantID, FavoriteDate) VALUES (#{userId}, #{merchantId}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "favoriteMerchantId")
    void addFavoriteMerchant(@Param("userId") Long userId, @Param("merchantId") Long merchantId);

    @Select("SELECT d.DishID, d.DishName, COUNT(f.userId) as count " +
            "FROM Dish d LEFT JOIN FavoriteDish f ON d.DishID = f.dishId " +
            "WHERE d.MerchantID = #{merchantId} " +
            "GROUP BY d.DishID, d.DishName")
    List<UserFavoriteDish> findDishFavoriteCountsByMerchant(@Param("merchantId") Long merchantId);

}
