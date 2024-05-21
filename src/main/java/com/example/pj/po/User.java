package com.example.pj.po;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private Integer userID;

    private Integer ecardID;

    private String userName;

    private String gender;

    private String role;

    private Integer age;


}
