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
    private Long quantity; // 查询得出？测试用，看看能不能表里没有、通过sql语句查询的时候可用；但orderItem怎么改
    private Float price;  // 查询得出？



}