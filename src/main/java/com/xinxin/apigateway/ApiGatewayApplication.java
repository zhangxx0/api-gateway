package com.xinxin.apigateway;

import com.xinxin.apigateway.filter.SimpleAuthFilter;
import com.xinxin.apigateway.filter.TokenFilter;
import com.xinxin.apigateway.resolver.HostAddrKeyResolver;
import com.xinxin.apigateway.resolver.UriKeyResolver;
import com.xinxin.apigateway.resolver.UserKeyResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    // TokenFilter在工程的启动类中注入
    /*@Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }

    @Bean
    public SimpleAuthFilter simpleAuthFilter() {
        return new SimpleAuthFilter();
    }*/

    /*@Bean
    public HostAddrKeyResolver hostAddrKeyResolver() {
        return new HostAddrKeyResolver();
    }*/

//    @Bean
//    public UriKeyResolver uriKeyResolver() {
//        return new UriKeyResolver();
//    }

//    @Bean
//    public UserKeyResolver userKeyResolver() {
//        return new UserKeyResolver();
//    }

}
