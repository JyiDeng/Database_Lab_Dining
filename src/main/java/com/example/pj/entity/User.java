package com.example.pj.entity;

import lombok.*;
import org.springframework.stereotype.Component;

//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Component
//@ConfigurationProperties(prefix = "demo.student")
public class User {

    private Long userId;

    private String userName;

    private String gender;

    private Long ecardId;

    private String role;

    private Integer age;

    private String password;


}
