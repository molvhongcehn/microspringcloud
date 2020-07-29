package com.molv.springcloud.service;

import com.molv.springcloud.entities.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component //千万不要忘记加Component注解
public class DeptServiceFallbackFactory implements FallbackFactory<DeptApiService> {
    @Override
    public DeptApiService create(Throwable throwable) {
        return new DeptApiService() {
            @Override
            public boolean add(Dept dept) {
                return false;
            }

            @Override
            public Dept get(Long id) {
                return new Dept().setDeptno(id).setDname("该ID：" + id + "没有对应的数据，Consumer客户端提供的降级信息，此服务Provider已经停止" +
                        "关闭").setDb_source("no this DataBase in the Mysql");
            }

            @Override
            public List<Dept> list() {
                return null;
            }
        };
    }
}
