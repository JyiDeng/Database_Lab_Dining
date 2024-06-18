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

public class Merchant {

    private Integer merchantId;

    private String merchantName;

    private String mainDishes;

    private String address;

    private String menuId;




}
