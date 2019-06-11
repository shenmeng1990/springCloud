package com.inspur.springcloud.controller;

import com.inspur.springcloud.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String sayHello(@RequestParam(defaultValue = "tomy",required = false) String name){
        return helloService.sayHi(name);
    }
}
