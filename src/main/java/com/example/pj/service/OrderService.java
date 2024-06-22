package com.example.pj.service;

import com.example.pj.entity.OrderItem;
import com.example.pj.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
//
//    @Autowired
//    private OrderMapper orderMapper;
//
//    public void updateOrder(Long menuItemId, Long quantity, Long merchantId) {
//        // 获取 DishID
//        Long dishId = orderMapper.findDishIdByMenuItemId(menuItemId);
//
//        // 获取 OrderID
//        Long orderId = orderMapper.findOrderIdByMerchantId(merchantId);
//
//        // 检查 OrderItem 是否存在
//        OrderItem orderItem = orderMapper.findOrderItem(orderId, dishId);
//
//        if (orderItem == null) {
//            // 如果不存在，则插入新的 OrderItem
//            orderMapper.insertOrderItem(orderId, dishId, quantity);
//        } else {
//            // 如果存在，则更新数量
//            if (quantity > 0) {
//                orderMapper.updateOrderItemQuantity(orderId, dishId, quantity);
//            } else {
//                orderMapper.deleteOrderItem(orderId, dishId);
//            }
//        }
//    }
}
