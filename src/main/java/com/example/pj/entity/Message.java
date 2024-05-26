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

public class Message {
    private Integer messageId;
    private Integer userId;
    private Integer adminId;
    private String content;
    private String messageDate;
}