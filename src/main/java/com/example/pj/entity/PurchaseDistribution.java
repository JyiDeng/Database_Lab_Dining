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
public class PurchaseDistribution {
    private Long dishId;
    private String dishName;
    private int userId;
    private int purchaseCount;
}
