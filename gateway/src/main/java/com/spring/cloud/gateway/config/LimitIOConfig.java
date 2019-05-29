package com.spring.cloud.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 *          限流策略
 *  只能加载一个bean到容器
 */
@Configuration
public class LimitIOConfig {

    //========================redis不要使用windows版本 有的命令不存在 ！==========================

    //根据请求参数中的 user 字段来限流--用户限流
/*    @Bean
    KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
    }*/

//    //根据请求 IP 地址来限流--IP限流
    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

    //获取请求地址的uri作为限流key--接口限流
/*    @Bean
    KeyResolver apiKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }*/
}
