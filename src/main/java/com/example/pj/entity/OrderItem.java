package com.example.pj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Component
public class OrderItem {
    private Long orderItemId;
    private Long orderId;
    private Long dishId;
    private Long quantity; // menuItem里的是查询得出，这个是orderItem
    private Float price;  // 查询得出
    private String dishName; // 查询得出



}