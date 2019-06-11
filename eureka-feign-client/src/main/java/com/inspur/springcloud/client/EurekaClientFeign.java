package com.inspur.springcloud.client;

import com.inspur.springcloud.config.FeignConfig;
import com.inspur.springcloud.hystrix.HelloHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "eureka-client",configuration = FeignConfig.class,fallback = HelloHystrix.class)
public interface EurekaClientFeign {
    @GetMapping("/hello")
    String sayHiFromClientEureka(@RequestParam(value="name") String name);
}
