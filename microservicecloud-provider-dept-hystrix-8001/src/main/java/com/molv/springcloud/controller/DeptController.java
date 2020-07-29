package com.molv.springcloud.controller;

import com.molv.springcloud.entities.Dept;
import com.molv.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService service;

    @Autowired
    private DiscoveryClient discoveryClient;//服务发现

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept) {
        return service.add(dept);
    }

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    //服务熔断,方法失败时进入的方法
    @HystrixCommand(fallbackMethod = "processHystrixGet")
    public Dept get(@PathVariable("id") Long id) {
        Dept dept = service.get(id);
        if(dept==null){
            throw new RuntimeException("该Id没有对应的信息");
        }else{
            return  dept;
        }
    }

    public Dept processHystrixGet(@PathVariable("id") Long id) {
        return new Dept().setDeptno(id).setDname("该ID" + id + "没有对应的数据").setDb_source("no this DataBase in Mysql");
    }

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list() {
        return service.list();
    }

    @RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
    public Object disvovery() {
        List<String> list = discoveryClient.getServices();
        System.out.println("服务发现：" + list);

        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("MICROSERVICECLOUD-DEPT");
        for (ServiceInstance element : serviceInstances) {
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t" + element.getUri());
        }
        return this.discoveryClient;
    }
}
