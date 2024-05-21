package com.example.pj.po;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
@Data
@Component
@ConfigurationProperties(prefix = "demo.student")
public class User {

    private Integer userID;

    private Integer ecardID;

    private String usrName;

    private String gender;

    private String role;

    private Integer age;


}
