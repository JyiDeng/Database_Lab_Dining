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
    private Integer reviewId;
    private Integer userId;
    private Integer merchantId;
    private Integer dishId;
    private Float rating;
    private String content;
    private String reviewDate;
}