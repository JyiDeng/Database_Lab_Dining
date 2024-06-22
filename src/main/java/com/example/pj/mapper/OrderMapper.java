package com.example.pj.mapper;

import com.example.pj.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    @Insert("INSERT INTO MyOrder (UserID, merchantID, OrderDate, Status, OrderType, TotalPrice) VALUES (#{userId}, #{merchantId}, #{orderDate}, #{status}, #{orderType}, #{totalPrice})")
    @Options(useGeneratedKeys = true, keyProperty = "orderId")
    void createOrder(MyOrder order);

    @Insert("INSERT INTO OrderItem (OrderID, DishID, Quantity, Price) VALUES (#{orderId}, #{dishId}, #{quantity}, #{price})")
    void insertOrderItem(@Param("orderId") Long orderId, @Param("dishId") Long dishId, @Param("quantity") Long quantity, @Param("price") Float price);

    @Select("SELECT COUNT(*) FROM myOrder WHERE merchantID = #{merchantID} AND Status = 'Pending'")
    Long countPendingOrders(@Param("merchantID") Long merchantId);

    @Select("SELECT * FROM myOrder o LEFT JOIN OrderItem oi ON o.OrderID = oi.OrderID WHERE o.merchantID = #{merchantID}")
    List<Map<String, Object>> getOrdersByMerchantId(@Param("merchantID") Long merchantId);


    @Select("SELECT * FROM MyOrder WHERE UserID = #{userId}")
    List<MyOrder> getOrdersByUserId(Long userId);

    @Select("SELECT oi.*, mp.price AS price " +
            "FROM OrderItem oi " +
            "JOIN menuItem mi ON oi.dishID = mi.dishID " +
//            "JOIN dish d ON oi.dishID = d.dishID " +
            "JOIN menuPrice mp ON mi.menuItemId = mp.menuItemID " +
            "WHERE oi.OrderID = #{orderId} " +
//            "AND d.merchantId = #{path} " +
            "AND mp.effectiveDate <= NOW() " +
            "AND (mp.endDate IS NULL OR mp.endDate >= NOW())")
    List<OrderItem> getOrderItemsByOrderId(String path, Long orderId);

    @Select("SELECT SUM(oi.Quantity) " +
            "FROM OrderItem oi " +
            "JOIN MyOrder o ON oi.OrderID = o.OrderID " +
            "WHERE oi.DishID = #{dishId} " +
            "AND o.OrderType = #{orderType}")
    Long getDishSalesByType(@Param("dishId") Long dishId, @Param("orderType") String orderType);

    @Delete("DELETE FROM OrderItem WHERE OrderID = #{orderId}")
    void deleteOrderItemsByOrderId(@Param("orderId") Long orderId);

}