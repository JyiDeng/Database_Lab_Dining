package com.example.pj.mapper;

import com.example.pj.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MerchantMapper {
//    @Select("SELECT * FROM merchant")
//    List<User> findAll();



    @Select("SELECT MerchantId, MerchantName, MainDishes FROM merchant WHERE MerchantName LIKE CONCAT('%', #{keyword}, '%') OR Address LIKE CONCAT('%', #{keyword}, '%')")
    List<Merchant> searchMerchant(@Param("keyword") String keyword);


    @Select("SELECT * FROM merchant WHERE MerchantID = #{id}")
    Merchant getMerchantByID(@Param("id") Long id);

    @Select("SELECT * FROM merchant WHERE MerchantID = #{merchantId}")
    Merchant getMerchantDetails(@Param("merchantId") Long merchantId);

//    @Select("SELECT * FROM dish WHERE MerchantID = #{merchantId}")
//    List<Dish> getDishesByMerchantID(@Param("merchantId") Long merchantId);



    @Select("SELECT d.DishID, d.DishName, COUNT(f.userId) as count " +
            "FROM Dish d LEFT JOIN FavoriteDish f ON d.DishID = f.dishId " +
            "WHERE d.MerchantID = #{merchantId} " +
            "GROUP BY d.DishID, d.DishName")
    List<UserFavoriteDish> findDishFavoriteCountsByMerchant(@Param("merchantId") Long merchantId);


}
