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
//    private Long menuId;
    private Long dishId;
    private Float price;
    private String dishName; // 测试可以：表里没有、通过sql语句查询的时候可用
//    private Integer quantity; // menuItem的quantity是查询得出的，OrderItem有货真价实的自己的属性
}
