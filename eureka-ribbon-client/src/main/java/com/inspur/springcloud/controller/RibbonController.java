package com.inspur.springcloud.controller;

import com.inspur.springcloud.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RibbonController {

    @Autowired
    RibbonService ribbonService;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/hello")
    public String sayHello(@RequestParam(required = false,defaultValue = "tomy") String name){
       return ribbonService.testHello(name);
    }

    @GetMapping("/testRibbon")
    public String testRibbon(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
        return serviceInstance.getHost()+":"+serviceInstance.getPort();
    }
}
