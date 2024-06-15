package com.example.pj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MenuPrice {
    private Long menuPriceId;
    private Long menuItemId;
    private Float price;
    private LocalDateTime effectiveDate;
    private LocalDateTime endDate;
}
