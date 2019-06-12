package com.spring.cloud.gateway.filter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

/**
 *  jwt验证的过滤器(token验证)
 *  请求格式：http://localhost:8000/client/hello?token=1111111
 */
public class JwtCheckGatewayFilterFactory extends AbstractGatewayFilterFactory<JwtCheckGatewayFilterFactory.Config> {

    public JwtCheckGatewayFilterFactory() {super(Config.class); }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            //构建返回
            ServerHttpResponse response = exchange.getResponse();
            //设置headers
            HttpHeaders httpHeaders = response.getHeaders();
            httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
            httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
            //设置body
            String bodyStr = "未登录或登录超时";

            try{
                //config.setToken(exchange.getRequest().getHeaders().getFirst("Authorization"));
                //================自测取的是url后面的参数===================
                String tk = exchange.getRequest().getURI().getQuery();
                if(tk.indexOf("token") > -1){
                    config.setToken(tk.substring(tk.indexOf("token=") + 6));//
                }
                //==========================================================
            }catch (Exception e){
                bodyStr = "token异常，请按[Url + ?token=xxx]的形式请求";
                DataBuffer bodyDataBuffer = response.bufferFactory().wrap(bodyStr.getBytes());
                return response.writeWith(Mono.just(bodyDataBuffer));
            }

        //校验jwtToken的合法性
        if (config.getToken() != null) {
        // 合法,将用户id作为参数传递下去
        return chain.filter(exchange); }

        //不合法(响应未登录的异常)
        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(bodyStr.getBytes());
        return response.writeWith(Mono.just(bodyDataBuffer));
        };
        }

        //一个放config对象属性的类
    public static class Config {

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
