package com.afa.adminback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@ComponentScan(basePackages = {"com.afa.adminback"})
public class AdminBackApplication {


    public static void main(String[] args) {
        SpringApplication.run(AdminBackApplication.class, args);
    }




}
