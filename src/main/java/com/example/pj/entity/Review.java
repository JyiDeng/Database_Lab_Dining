package com.example.pj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Review {
    private Long reviewId;
    private Long userId;
//    private Long merchantId;
    private Long dishId;
    private Float rating;
    private String content;
    private String reviewDate;
    private String dishName; // 查询用
}