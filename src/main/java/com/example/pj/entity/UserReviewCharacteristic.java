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
public class UserReviewCharacteristic {
    private String role;
    private int age;
    private String gender;
    private int reviewCount;
    private double averageRating;
}
