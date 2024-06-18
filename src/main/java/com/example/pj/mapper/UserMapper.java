package com.example.pj.mapper;

import com.example.pj.entity.Merchant;
import com.example.pj.entity.Review;
import com.example.pj.entity.User;
import com.example.pj.entity.MyOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user")
    List<User> findAll();

    @Select("SELECT * FROM user WHERE UserID = #{userId}")
    User findByID(Long id);

    @Select("SELECT * FROM user WHERE UserName = #{userName}")
    User findByUsername(String userName);

    @Insert("INSERT INTO user (UserId,UserName,Gender,EcardId,Role,Age,Password) VALUES (#{userID},#{userName},#{gender},#{ecardID},#{role},#{age},#{password})")
    void insert(User user);

    @Update("UPDATE user SET UserName = #{userName}, Gender = #{gender}, EcardID = #{ecardID}, Role = #{role}, Age = #{age}, Password = #{password} WHERE UserID = #{userID}")
    void update(User user);

    @Delete("DELETE FROM user WHERE UserID = #{userId}")
    void delete(Long id);

    int save(@Param("user") User user);

    // 查询订单
    @Select("SELECT * FROM MyOrder WHERE UserID = #{userId}")
    List<MyOrder> findOrdersByUserId(Long userId);

    // 收藏菜品
    @Insert("INSERT INTO FavoriteDish (userID, dishID, FavoriteDate) VALUES (#{userId}, #{dishId}, NOW())")
    void favoriteDish(@Param("userId") Long userId, @Param("dishId") Long dishId);

    // 收藏商户
    @Insert("INSERT INTO FavoriteMerchant (userID, merchantID, FavoriteDate) VALUES (#{userId}, #{merchantId}, NOW())")
    void favoriteMerchant(@Param("userId") Long userId, @Param("merchantId") Long merchantId);

    @Select("SELECT * FROM Review WHERE dishId = #{dishId}")
    List<Review> dishReview(@Param("dishId") Long dishId);
}
