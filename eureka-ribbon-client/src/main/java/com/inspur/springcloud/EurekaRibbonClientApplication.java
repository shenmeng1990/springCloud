package com.inspur.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
public class EurekaRibbonClientApplication {

    @Bean
	public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet  streamServlet=new HystrixMetricsStreamServlet();
        ServletRegistrationBean servletRegistrationBean=new ServletRegistrationBean(streamServlet);
        servletRegistrationBean.setLoadOnStartup(1);
        servletRegistrationBean.addUrlMappings("/hystrix.stream");
        servletRegistrationBean.setName("HystrixMetricsStreamServlet");
        return servletRegistrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(EurekaRibbonClientApplication.class, args);
	}

}
