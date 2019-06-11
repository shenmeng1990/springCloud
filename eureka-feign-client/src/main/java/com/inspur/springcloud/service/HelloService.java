package com.inspur.springcloud.service;

import com.inspur.springcloud.client.EurekaClientFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    @Autowired
    EurekaClientFeign eurekaClientFeign;

    public String sayHi(String name){
        return eurekaClientFeign.sayHiFromClientEureka(name);
    }
}
