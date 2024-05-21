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
    private Long id;
    private Long merchantId;
    private Long userId;
    private String comment;
    private Double rating;
    // getters and setters
}