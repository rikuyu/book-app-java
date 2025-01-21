package com.example.bookapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.bookapp.infra.mapper")
public class BookAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookAppApplication.class, args);
    }

}
