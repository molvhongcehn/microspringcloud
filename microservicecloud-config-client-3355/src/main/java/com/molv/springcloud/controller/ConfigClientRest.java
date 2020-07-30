package com.molv.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientRest {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${eureka.client.service-url.defalutZone}")
    private String eurekaServers;

    @Value("${server.port}")
    private  String port;

    @RequestMapping("/config")
    public String getConfig() {
        String str = "applicationName:"+applicationName+",eurekaServers:"+eurekaServers+",port:"+port;
        System.out.println(str);
        return str;
    }
}
