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

    // 商户忠实粉丝的消费分布
    @Select("WITH LoyalCustomers AS (" +
            "    SELECT o.UserID " +
            "    FROM MyOrder o " +
            "    WHERE o.MerchantID = #{merchantId} " +
            "    AND o.OrderDate >= DATE_SUB(NOW(), INTERVAL #{timePeriod}) " +
            "    GROUP BY o.UserID " +
            "    HAVING COUNT(o.OrderID) > #{threshold} " +
            ") " +
            "SELECT d.DishID, d.DishName, lc.UserID, SUM(oi.Quantity) AS PurchaseCount " +
            "FROM LoyalCustomers lc " +
            "JOIN MyOrder o ON lc.UserID = o.UserID " +
            "JOIN OrderItem oi ON o.OrderID = oi.OrderID " +
            "JOIN Dish d ON oi.DishID = d.DishID " +
            "WHERE o.MerchantID = #{merchantId} " +
            "AND o.OrderDate >= DATE_SUB(NOW(), INTERVAL #{timePeriod}) " +
            "GROUP BY d.DishID, d.DishName, lc.UserID " +
            "ORDER BY d.DishID, lc.UserID")
    List<PurchaseDistribution> getLoyalCustomerDistribution(@Param("merchantId") int merchantId, @Param("timePeriod") String timePeriod, @Param("threshold") int threshold);
}
