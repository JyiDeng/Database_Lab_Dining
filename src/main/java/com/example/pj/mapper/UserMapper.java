package com.example.pj.mapper;

import com.example.pj.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM User")
    List<User> findAll();

    @Select("SELECT * FROM User WHERE UserID = #{userId}")
    User findByID(Long id);

    @Select("SELECT * FROM User WHERE UserName = #{userName}")
    User findByUsername(String userName);

    @Insert("INSERT INTO User (UserId,UserName,Gender,EcardId,Role,Age,Password) VALUES (#{userId},#{userName},#{gender},#{ecardId},#{role},#{age},#{password})")
    void insert(User user);
    // 为了让数据库真的加进去这个人，既可以传user又可以传参数吗，可以的 TODO

    @Update("UPDATE User SET UserName = #{userName}, Gender = #{gender}, EcardID = #{ecardId}, Role = #{role}, Age = #{age}, Password = #{password} WHERE UserID = #{userId}")
    void update(User user);

    @Delete("DELETE FROM User WHERE UserID = #{userId}")
    void delete(Long id);

    int save(@Param("user") User user);

    // 用户活跃度分析
//    @Select("SELECT UserID, YEARWEEK(OrderDate, 1) AS YEARWEEK, COUNT(*) AS OrderCount " +
//            "FROM MyOrder " +
//            "GROUP BY UserID, YEARWEEK(OrderDate, 1) " +
//            "ORDER BY UserID, YearWeek")
    @Select("SELECT" +
            "    o.UserID," +
            "    YEARWEEK(o.OrderDate, 1) AS period," +
            "    COALESCE(SUM(CASE WHEN o.OrderType = 'Queue' THEN 1 ELSE 0 END), 0) AS QueueCounts," +
            "    COALESCE(SUM(CASE WHEN o.OrderType = 'Online' THEN 1 ELSE 0 END), 0) AS OnlineCounts " +
            "FROM MyOrder o " +
            "WHERE" +
            "    o.UserID = #{userId}" +
            "    AND o.OrderDate >= DATE_SUB(NOW(), INTERVAL #{timePeriod}+1 WEEK)" +
            "    AND o.OrderDate < DATE_SUB(NOW(), INTERVAL #{timePeriod} WEEK) " +
            "GROUP BY" +
            "    o.UserID," +
            "    YEARWEEK(o.OrderDate, 1) " +
            "ORDER BY" +
            "    o.UserID," +
            "    YEARWEEK(o.OrderDate, 1); ")
    List<UserActivity> getWeeklyActivity(Long userId,int timePeriod);

    @Select("SELECT" +
            "    o.UserID," +
            "    YEARWEEK(o.OrderDate, 1) AS period," +
            "    COALESCE(SUM(CASE WHEN o.OrderType = 'Queue' THEN 1 ELSE 0 END), 0) AS QueueCounts," +
            "    COALESCE(SUM(CASE WHEN o.OrderType = 'Online' THEN 1 ELSE 0 END), 0) AS OnlineCounts " +
            "FROM MyOrder o " +
            "WHERE" +
            "    o.UserID = #{userId}" +
            "    AND o.OrderDate >= DATE_SUB(NOW(), INTERVAL #{timePeriod}+1 MONTH)" +
            "    AND o.OrderDate < DATE_SUB(NOW(), INTERVAL #{timePeriod} MONTH) " +
            "GROUP BY" +
            "    o.UserID," +
            "    YEARWEEK(o.OrderDate, 1) " +
            "ORDER BY" +
            "    o.UserID," +
            "    YEARWEEK(o.OrderDate, 1); ")
    List<UserActivity> getMonthlyActivity(Long userId,int timePeriod);

    @Select("SELECT" +
            "    o.UserID," +
            "    YEARWEEK(o.OrderDate, 1) AS period," +
            "    COALESCE(SUM(CASE WHEN o.OrderType = 'Queue' THEN 1 ELSE 0 END), 0) AS QueueCounts," +
            "    COALESCE(SUM(CASE WHEN o.OrderType = 'Online' THEN 1 ELSE 0 END), 0) AS OnlineCounts " +
            "FROM MyOrder o " +
            "WHERE" +
            "    o.UserID = #{userId}" +
            "    AND o.OrderDate >= DATE_SUB(NOW(), INTERVAL #{timePeriod}+1 YEAR)" +
            "    AND o.OrderDate < DATE_SUB(NOW(), INTERVAL #{timePeriod} YEAR) " +
            "GROUP BY" +
            "    o.UserID," +
            "    YEARWEEK(o.OrderDate, 1) " +
            "ORDER BY" +
            "    o.UserID," +
            "    YEARWEEK(o.OrderDate, 1); ")
    List<UserActivity> getYearlyActivity(Long userId,int timePeriod);

    @Select("SELECT UserID, " +
            "CASE " +
            "WHEN HOUR(OrderDate) BETWEEN 6 AND 12 THEN 'Morning' " +
            "WHEN HOUR(OrderDate) BETWEEN 12 AND 18 THEN 'Afternoon' " +
            "ELSE 'Evening' " +
            "END AS TimeOfDay, " +
            "COUNT(*) AS OrderCount " +
            "FROM MyOrder " +
            "WHERE userId = #{userId}" +
            "GROUP BY UserID, TimeOfDay " +
            "ORDER BY UserID, TimeOfDay")
    List<UserActivity> getActivityByTimeOfDay(Long userId);

    // 用户群体特征分析
    @Select("SELECT u.Age as characteristic, d.DishName, COUNT(oi.Quantity) AS PurchaseCount " +
            "FROM User u " +
            "JOIN MyOrder o ON u.UserID = o.UserID " +
            "JOIN OrderItem oi ON o.OrderID = oi.OrderID " +
            "JOIN Dish d ON oi.DishID = d.DishID " +
            "GROUP BY u.Age, d.DishID, d.DishName " +
            "ORDER BY u.Age, d.DishID")
    List<UserCharacteristic> getCharacteristicsByAge();

    @Select("SELECT u.Gender as characteristic, d.DishName, COUNT(oi.Quantity) AS PurchaseCount " +
            "FROM User u " +
            "JOIN MyOrder o ON u.UserID = o.UserID " +
            "JOIN OrderItem oi ON o.OrderID = oi.OrderID " +
            "JOIN Dish d ON oi.DishID = d.DishID " +
            "GROUP BY u.Gender, d.DishID, d.DishName " +
            "ORDER BY u.Gender, d.DishID")
    List<UserCharacteristic> getCharacteristicsByGender();

    @Select("SELECT u.Role as characteristic, d.DishName, COUNT(oi.Quantity) AS PurchaseCount " +
            "FROM User u " +
            "JOIN MyOrder o ON u.UserID = o.UserID " +
            "JOIN OrderItem oi ON o.OrderID = oi.OrderID " +
            "JOIN Dish d ON oi.DishID = d.DishID " +
            "GROUP BY u.Role, d.DishID, d.DishName " +
            "ORDER BY u.Role, d.DishID")
    List<UserCharacteristic> getCharacteristicsByRole();

    @Select("SELECT u.Role, u.Age, u.Gender, COUNT(r.ReviewID) AS ReviewCount, AVG(r.Rating) AS AverageRating " +
            "FROM User u " +
            "JOIN Review r ON u.UserID = r.UserID " +
            "GROUP BY u.Role, u.Age, u.Gender " +
            "ORDER BY u.Role, u.Age, u.Gender")
    List<UserReviewCharacteristic> getReviewCharacteristics();

// 商户忠实粉丝的消费分布
    @Select("WITH LoyalCustomers AS (" +
            "    SELECT o.UserID " +
            "    FROM MyOrder o " +
            "    WHERE o.MerchantID = #{merchantId} " +
            "    AND o.OrderDate >= DATE_SUB(NOW(), INTERVAL #{timePeriod} MONTH) " +
            "    GROUP BY o.UserID " +
            "    HAVING COUNT(o.OrderId) > #{threshold} " +
            ") " +
            "SELECT d.DishID, d.DishName, lc.UserID, SUM(oi.Quantity) AS PurchaseCount " +
            "FROM LoyalCustomers lc " +
            "JOIN MyOrder o ON lc.UserID = o.UserID " +
            "JOIN OrderItem oi ON o.OrderID = oi.OrderID " +
            "JOIN Dish d ON oi.DishID = d.DishID " +
            "WHERE o.MerchantID = #{merchantId} " +
            "AND o.OrderDate >= DATE_SUB(NOW(), INTERVAL #{timePeriod} MONTH) " +
            "GROUP BY d.DishID, d.DishName, lc.UserID " +
            "ORDER BY d.DishID, lc.UserID")
    List<PurchaseDistribution> getLoyalCustomerDistribution(@Param("merchantId") Long merchantId, @Param("timePeriod") String timePeriod, @Param("threshold") Long threshold);
}

