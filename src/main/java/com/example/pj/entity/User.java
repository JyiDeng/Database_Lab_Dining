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

    private Long userID;

    private String userName;

    private String gender;

    private Long ecardID;

    private String role;

    private Integer age;


}
