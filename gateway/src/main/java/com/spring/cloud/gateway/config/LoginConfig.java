package com.spring.cloud.gateway.config;

import com.spring.cloud.gateway.filter.JwtCheckGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  token验证配置
 */

@Configuration
public class LoginConfig {

    @Bean
    public JwtCheckGatewayFilterFactory jwtCheckGatewayFilterFactory(){return new JwtCheckGatewayFilterFactory(); }
}
