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
public class Dish {
    private Long id;
    private String name;
    private Double price;
    private String imageUrl;
    private String description;
    private String ingredients;
    private String nutrition;
    private String allergens;
    private Long merchantId;

}