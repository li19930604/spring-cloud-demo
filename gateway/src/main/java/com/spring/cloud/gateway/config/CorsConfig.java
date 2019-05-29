package com.spring.cloud.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 *  跨域处理
 *  域名不同 例如 www.baidu.com / www.sina.com
 *  协议不同 例如 https://www.123.com / http://www.123.com
 *  端口不同 例如 www.123.com:80 / www.123.com:1433
 *  以上都是跨域
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //在生产环境上最好指定域名，以免产生跨域安全问题
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(source);
    }
}
