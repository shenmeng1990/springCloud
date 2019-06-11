package com.inspur.springcloud.controller;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RibbonController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/testRibbon")
    public String testRibbon(){
        ServiceInstance instance = loadBalancerClient.choose("stores");
        return instance.getHost()+":"+instance.getPort();
    }
}
