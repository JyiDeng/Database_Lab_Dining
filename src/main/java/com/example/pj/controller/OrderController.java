package com.example.pj.controller;

import com.example.pj.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private DishMapper dishMapper;

//    @PostMapping("/create")
//    public String createOrder(@RequestBody MyOrder order) {
//        order.setOrderDate(LocalDateTime.now());
//        order.setStatus("Pending");
//        orderMapper.createOrder(order);
////        orderMapper.insertOrderItem(order.getOrderId(), order.getOrderItems());
//        return "Order created successfully with ID: " + order.getOrderId();
//    }




//    @RequestMapping("/{path}/createOrder")
//    public void createOrder2(@PathVariable String path,@RequestParam Long userID,  Long merchantID,  String status, String orderType) {
//        MyOrder newOrder = new MyOrder();
//        newOrder.setUserId(userID);
//        newOrder.setMerchantId(merchantID);
//        newOrder.setStatus(status);
//        newOrder.setOrderType(orderType);
//        newOrder.setTotalPrice(0F);  // 初始化为0，Float类型
//        orderMapper.createOrder(newOrder);
//
////        return "redirect:/orders/" + merchantID;
//    }
//    @RequestMapping("/updateOrder")
//    public String updateOrder(@RequestBody Map<String, Object> orderData) {
//        Long orderId = (Long) orderData.get("orderId");
//        Map<String, Integer> items = (Map<String, Integer>) orderData.get("items");
//
//        // 删除现有的OrderItem记录
//        orderMapper.deleteOrderItemsByOrderId(orderId);
//
//        // 插入新的OrderItem记录
//        for (Map.Entry<String, Integer> entry : items.entrySet()) {
//            Long menuItemId = Long.valueOf(entry.getKey());
//            Long quantity = Long.valueOf(entry.getValue());
//            // 你可能需要从数据库中获取价格，根据menuItemId来查询
//            Float price = 10.0; // 假设为10.0，实际应根据menuItemId查询
//            orderMapper.insertOrderItem(orderId, menuItemId, quantity, price);
//        }
//
//        return "Order updated successfully!";
//    }

//    @GetMapping("/user/{userId}")
//    public List<MyOrder> getOrdersByUserId(@PathVariable Long userId) {
//        List<MyOrder> orders = orderMapper.getOrdersByUserId(userId);
//        for (MyOrder order : orders) {
//            List<OrderItem> items = orderMapper.getOrderItemsByOrderId(order.getOrderId());
//            order.setOrderItems(items);
//        }
//        return orders;
//    }

//    @GetMapping("/dish/{dishId}/sales")
//    public SalesData getSalesDataByDishId(@PathVariable Long dishId) {
//        int onlineSales = orderMapper.getDishSalesByType(dishId, "Online");
//        int queueSales = orderMapper.getDishSalesByType(dishId, "Queue");
//        return new SalesData(onlineSales, queueSales);
//    }

    @GetMapping("/dish/{dishId}/top-buyer")
    public Map<String, Object> getTopBuyerForDish(@PathVariable Long dishId) {
        return dishMapper.getTopBuyerForDish(dishId);
    }

    public static class SalesData {
        private int onlineSales;
        private int queueSales;

        public SalesData(int onlineSales, int queueSales) {
            this.onlineSales = onlineSales;
            this.queueSales = queueSales;
        }

        public int getOnlineSales() {
            return onlineSales;
        }

        public void setOnlineSales(int onlineSales) {
            this.onlineSales = onlineSales;
        }

        public int getQueueSales() {
            return queueSales;
        }

        public void setQueueSales(int queueSales) {
            this.queueSales = queueSales;
        }
    }
}
