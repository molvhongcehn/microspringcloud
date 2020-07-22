package com.molv.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DeptConsumer8050_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer8050_App.class,args);
    }
}
