package com.example.pj.mapper;

import com.example.pj.entity.*;
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

    @Insert("INSERT INTO user (UserId,UserName,Gender,EcardId,Role,Age,Password) VALUES (#{userId},#{userName},#{gender},#{ecardID},#{role},#{age},#{password})")
    void insert(User user);
    // 为了让数据库真的加进去这个人，既可以传user又可以传参数吗

    @Update("UPDATE user SET UserName = #{userName}, Gender = #{gender}, EcardID = #{ecardID}, Role = #{role}, Age = #{age}, Password = #{password} WHERE UserID = #{userId}")
    void update(User user);

    @Delete("DELETE FROM user WHERE UserID = #{userId}")
    void delete(Long id);

    int save(@Param("user") User user);

    // 查询订单
    @Select("SELECT * FROM MyOrder WHERE UserID = #{userId}")
    List<MyOrder> findOrdersByUserId(Long userId);

    // 收藏菜品
    @Select("SELECT fd.*, d.dishName as dishName " +
            "FROM FavoriteDish fd " +
            "JOIN dish d ON fd.dishId = d.dishId " +
            "WHERE fd.userId = #{userId} " +
            "AND fd.dishId <> #{dishId}")
    List<UserFavoriteDish> findFavoriteDish(@Param("userId") Long userId, @Param("dishId") Long dishId);

    @Insert("INSERT INTO FavoriteDish (userId, dishID, FavoriteDate) " +
            "VALUES (#{userId}, #{dishId}, NOW())")
    List<UserFavoriteDish> addFavoriteDish(@Param("userId") Long userId, @Param("dishId") Long dishId);

    // 收藏商户
    @Select("SELECT * FROM FavoriteMerchant")
    List<UserFavoriteMerchant> findFavoriteMerchant(@Param("userId") Long userId);

    @Insert("INSERT INTO FavoriteMerchant (userId, merchantID, FavoriteDate) VALUES (#{userId}, #{merchantId}, NOW())")
    List<UserFavoriteMerchant> addFavoriteMerchant(@Param("userId") Long userId, @Param("merchantId") Long merchantId);

    @Select("SELECT * FROM Review WHERE dishId = #{dishId}")
    List<Review> dishReview(@Param("dishId") Long dishId);

    @Select("SELECT * FROM message WHERE user_id = #{userId}")
    List<Message> getMessagesByUserId(int userId);
}
