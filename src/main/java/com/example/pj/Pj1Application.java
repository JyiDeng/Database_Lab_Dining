package com.example.pj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class Pj1Application {

	public static void main(String[] args) {
		SpringApplication.run(Pj1Application.class, args);
	}

}
