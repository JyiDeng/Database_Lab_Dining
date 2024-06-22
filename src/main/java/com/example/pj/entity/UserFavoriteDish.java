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
public class UserFavoriteDish {
    private Long favoriteDishId;
    private Long userId;
    private Long dishId;
    private String dishName; // 查询用
    private String favoriteDate;

}