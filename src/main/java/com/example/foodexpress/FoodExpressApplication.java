package com.example.foodexpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FoodExpressApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodExpressApplication.class, args);
    }

}
