package com.example.pj.controller;

import com.example.pj.entity.*;
import com.example.pj.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private DishMapper dishMapper;

    @PostMapping("/create")
    public String createOrder(@RequestBody MyOrder order) {
        order.setOrderDate(LocalDateTime.now().toString());
        order.setStatus("Pending");
        orderMapper.insertOrder(order);
        orderMapper.insertOrderItems(order.getOrderId(), order.getOrderItems());
        return "Order created successfully with ID: " + order.getOrderId();
    }

    @GetMapping("/user/{userId}")
    public List<MyOrder> getOrdersByUserId(@PathVariable Long userId) {
        List<MyOrder> orders = orderMapper.getOrdersByUserId(userId);
        for (MyOrder order : orders) {
            List<OrderItem> items = orderMapper.getOrderItemsByOrderId(order.getOrderId());
            order.setOrderItems(items);
        }
        return orders;
    }

    @GetMapping("/dish/{dishId}/sales")
    public SalesData getSalesDataByDishId(@PathVariable Long dishId) {
        int onlineSales = orderMapper.getDishSalesByType(dishId, "Online");
        int queueSales = orderMapper.getDishSalesByType(dishId, "Queue");
        return new SalesData(onlineSales, queueSales);
    }

    @GetMapping("/merchant/{merchantId}/dishes/ratings-sales")
    public List<Map<String, Object>> getDishRatingsAndSalesByMerchantId(@PathVariable Long merchantId) {
        return orderMapper.getDishRatingsAndSalesByMerchantId(merchantId);
    }

    @GetMapping("/dish/{dishId}/top-buyer")
    public Map<String, Object> getTopBuyerForDish(@PathVariable Long dishId) {
        return orderMapper.getTopBuyerForDish(dishId);
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
