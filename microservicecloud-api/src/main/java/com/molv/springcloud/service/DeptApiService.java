package com.molv.springcloud.service;

import com.molv.springcloud.entities.Dept;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

//@FeignClient(value = "MICROSERVICECLOUD-DEPT")
@FeignClient(value = "MICROSERVICECLOUD-DEPT",fallbackFactory = DeptServiceFallbackFactory.class)
public interface DeptApiService {

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(Dept dept);

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    //public Dept get(Long id);//没有加上PathVariable会一直get结果都是null
    public Dept get(@PathVariable("id") Long id);

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list();
}
