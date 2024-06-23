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

//    @Insert("INSERT INTO OrderItem (OrderID, DishID, Quantity, Price) VALUES (#{orderId}, #{dishId}, #{quantity}, #{price})")
//    void insertOrderItem(@Param("orderId") Long orderId, @Param("dishId") Long dishId, @Param("quantity") Long quantity, @Param("price") Float price);

    @Select("SELECT COUNT(*) FROM myOrder " +
            "WHERE merchantID = #{merchantID} " +
            "AND userId = #{userId} " +
            "AND Status = 'Pending'")
    Long countPendingOrders(@Param("merchantID") Long merchantId, Long userId);



    @Select("SELECT * FROM myOrder o LEFT JOIN OrderItem oi ON o.OrderID = oi.OrderID WHERE o.merchantID = #{merchantID}")
    List<Map<String, Object>> getOrdersByMerchantId(@Param("merchantID") Long merchantId);

    // 查询订单
    @Select("SELECT * FROM MyOrder WHERE UserID = #{userId}")
    List<MyOrder> getOrdersByUserId(Long userId);

    @Select("SELECT oi.*, mp.price AS price,d.dishName as dishName " +
            "FROM OrderItem oi " +
            "JOIN menuItem mi ON oi.dishID = mi.dishID " +
            "left JOIN dish d ON oi.dishID = d.dishID " +
            "JOIN menuPrice mp ON mi.menuItemId = mp.menuItemID " +
            "WHERE oi.OrderID = #{orderId} " +
            "AND mp.effectiveDate = " +
            "(SELECT MAX(effectiveDate) " +
            "FROM menuPrice " +
            "WHERE menuItemId = mi.menuItemId) "
//            "AND d.merchantId = #{path} " +
//            "AND mp.effectiveDate <= NOW() " +
//            "AND (mp.endDate IS NULL OR mp.endDate >= NOW())"
            )
    List<OrderItem> getOrderItemsByOrderId(String path, Long orderId);

    @Select("SELECT SUM(oi.Quantity) " +
            "FROM OrderItem oi " +
            "JOIN MyOrder o ON oi.OrderID = o.OrderID " +
            "WHERE oi.DishID = #{dishId} " +
            "AND o.OrderType = #{orderType}")
    Long getDishSalesByType(@Param("dishId") Long dishId, @Param("orderType") String orderType);

    @Delete("DELETE FROM OrderItem WHERE OrderID = #{orderId}")
    void deleteOrderItemsByOrderId(@Param("orderId") Long orderId);

    /*
    @Select("SELECT dishID, AVG(AvgRating) as avgRating, SUM(quantity) as totalSales " +
            "FROM Dish d JOIN OrderItem oi ON d.DishID = oi.DishID " +
            "JOIN MyOrder o ON oi.OrderID = o.OrderID " +
            "WHERE d.MerchantID = #{merchantId} " +
            "GROUP BY dishID")
    List<Map<String, Object>> getDishRatingsAndSalesByMerchantId(Long merchantId);
    */

    @Select("SELECT dishID FROM menuItem WHERE menuItemId = #{menuItemId}")
    Long findDishIdByMenuItemId(Long menuItemId);

    @Select("SELECT OrderID FROM MyOrder WHERE merchantID = #{merchantId} AND Status = 'Pending' LIMIT 1")
    Long findOrderIdByMerchantId(Long merchantId);

    @Select("SELECT * FROM OrderItem WHERE OrderID = #{orderId} AND DishID = #{dishId}")
    OrderItem findOrderItem(Long orderId, Long dishId);

    @Insert("INSERT INTO OrderItem (OrderID, DishID, Quantity) VALUES (#{orderId}, #{dishId}, #{quantity})")
    @Options(useGeneratedKeys = true, keyProperty = "orderItemId")
    void insertOrderItem(Long orderId, Long dishId, Long quantity);

    @Update("UPDATE OrderItem SET Quantity = quantity + #{quantity} WHERE OrderID = #{orderId} AND DishID = #{dishId}")
    void updateOrderItemQuantity(Long orderId, Long dishId, Long quantity);

    @Delete("DELETE FROM OrderItem WHERE OrderID = #{orderId} AND DishID = #{dishId}")
    void deleteOrderItem(Long orderId, Long dishId);

    @Update("UPDATE MyOrder SET status = 'Completed' WHERE orderId = #{orderId}")
    void orderSubmitUpdateCompleted(Long orderId);

    @Update("UPDATE MyOrder SET status = 'Ended' WHERE orderId = #{orderId}")
    void orderAcceptUpdateEnded(Long orderId);
}