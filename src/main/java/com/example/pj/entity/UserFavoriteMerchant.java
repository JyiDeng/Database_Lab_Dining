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
public class UserFavoriteMerchant {
    private Long favoriteMerchantId;
    private Long userId;
    private Long merchantId;
    private String merchantName; //查询用
    private String favoriteDate;
}
