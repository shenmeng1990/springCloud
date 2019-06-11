package com.inspur.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RibbonService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "error")
    public String testHello(String name){
        String str=restTemplate.getForObject("http://eureka-client/hello?name="+name,String.class);
        return str;
    }

    public String error(String name){
        return "hello,"+name+",sorry,error!";
    }
}
