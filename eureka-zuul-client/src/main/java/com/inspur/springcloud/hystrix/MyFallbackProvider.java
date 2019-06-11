package com.inspur.springcloud.hystrix;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Component
public class MyFallbackProvider implements FallbackProvider{

    @Override
    public String getRoute() {
        return "eureka-client";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if(cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return response(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ClientHttpResponse response(final HttpStatus status) {
        return new ClientHttpResponse() {
            @Override
            public HttpHeaders getHeaders() {
                // headers 设定
                HttpHeaders headers = new HttpHeaders();
                MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
                headers.setContentType(mt);
                return headers;
            }

            @Override
            public InputStream getBody() throws IOException {
                // 响应体
                return new ByteArrayInputStream("服务不可用，请稍后再试。".getBytes("UTF-8"));
            }

            @Override
            public String getStatusText() throws IOException {
                // 返回状态文本
                return status.getReasonPhrase();
            }

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                // 返回数字类型的状态码
                return status.value();
            }

            @Override
            public void close() {
            }
        };
    }
}
