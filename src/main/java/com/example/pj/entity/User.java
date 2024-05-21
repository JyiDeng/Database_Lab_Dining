package com.example.pj.entity;

import lombok.*;
import org.springframework.stereotype.Component;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
@Data
@Component
//@ConfigurationProperties(prefix = "demo.student")
public class User {

    private Integer userID;

    private Integer ecardID;

    private String usrName;

    private String gender;

    private String role;

    private Integer age;


}
