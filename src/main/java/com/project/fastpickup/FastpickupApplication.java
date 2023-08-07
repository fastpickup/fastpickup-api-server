package com.project.fastpickup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.project.fastpickup.**.mappers")
public class FastpickupApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastpickupApplication.class, args);
    }
}
