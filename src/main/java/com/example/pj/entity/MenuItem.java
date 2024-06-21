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
public class MenuItem {
    private Long menuItemId;
    private Long menuId;
    private Long dishId;
    private Float price;
    private String dishName; // 测试可以：看看能不能表里没有、通过sql语句查询的时候可用
//    private Integer quantity; // 测试用，看看能不能表里没有、通过sql语句查询的时候可用；但orderItem怎么改
}
