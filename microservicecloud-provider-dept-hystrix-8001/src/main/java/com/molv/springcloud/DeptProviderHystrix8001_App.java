package com.molv.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient //服务发现
@EnableCircuitBreaker//对熔断的支持
public class DeptProviderHystrix8001_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptProviderHystrix8001_App.class,args);
    }
}
