package com.spring.cloud.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  读取本地配置
 *
 */

@RefreshScope
@RestController
@EnableEurekaClient
@SpringBootApplication
public class ConfigClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    @Value("${name}")  // 从对应的配置中心找到文件并把属性注入到value值中
    private String name;
    @Value("${name2}")  // 从对应的配置中心找到文件并把属性注入到value值中
    private String name2;

    @RequestMapping("/getData")
    public String getData(){
        return "=========="+name+ " || "+name2 +"==========";
    }
}

