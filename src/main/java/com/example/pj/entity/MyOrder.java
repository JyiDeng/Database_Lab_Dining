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
public class MyOrder {
    private Long orderId;
    private Long userId;
    private String orderDate;
    private String status;
    private String orderType;
    private Float totalAmount;


}