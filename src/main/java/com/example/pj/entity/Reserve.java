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
public class Reserve {
    private Long reserveId;
    private Long userId;
    private Long merchantId;
    private LocalDateTime reserveDate;
    private String merchantName; // 查询用

}
