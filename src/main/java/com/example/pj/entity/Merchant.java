package com.example.pj.entity;

import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component

public class Merchant {

    private Integer merchantID;

    private String merchantName;

    private String address;

    private Float avgRating;



}
