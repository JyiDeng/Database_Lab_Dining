package com.example.pj.mapper;

import com.example.pj.entity.*;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert("INSERT INTO MyOrder (UserID, merchantID, OrderDate, Status, OrderType, TotalPrice) VALUES (#{userId}, #{merchantId}, #{orderDate}, #{status}, #{orderType}, #{totalPrice})")
    @Options(useGeneratedKeys = true, keyProperty = "orderId")
    void insertOrder(MyOrder order);

    @Insert({
            "<script>",
            "INSERT INTO OrderItem (OrderID, DishID, Quantity, Price) VALUES ",
            "<foreach collection='orderItems' item='item' separator=','>",
            "(#{orderId}, #{item.dishId}, #{item.quantity}, #{item.price})",
            "</foreach>",
            "</script>"
    })
    void insertOrderItems(@Param("orderId") Long orderId, @Param("orderItems") List<OrderItem> orderItems);

    @Select("SELECT * FROM MyOrder WHERE UserID = #{userId}")
    List<MyOrder> getOrdersByUserId(Long userId);

    @Select("SELECT * FROM OrderItem WHERE OrderID = #{orderId}")
    List<OrderItem> getOrderItemsByOrderId(Long orderId);

    @Select("SELECT SUM(oi.Quantity) FROM OrderItem oi JOIN MyOrder o ON oi.OrderID = o.OrderID WHERE oi.DishID = #{dishId} AND o.OrderType = #{orderType}")
    int getDishSalesByType(@Param("dishId") Long dishId, @Param("orderType") String orderType);

}
