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
    private Long DishId;
    private String dishName;
    private Float price;
    private String category;
    private String description;
    private String picture;
    private String flavor;
    private String ingredients;
    private String allergens;
    private String nutritionInfo;
    private Float avgRating;
    private Long merchantId;

}