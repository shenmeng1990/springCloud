package com.inspur.springcloud.hystrix;

import com.inspur.springcloud.client.EurekaClientFeign;
import org.springframework.stereotype.Component;

@Component
public class HelloHystrix implements EurekaClientFeign{
    @Override
    public String sayHiFromClientEureka(String name) {
        return "hello,"+name+",sorry,error!";
    }
}
