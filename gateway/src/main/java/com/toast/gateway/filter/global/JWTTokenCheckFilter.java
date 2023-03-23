package com.toast.gateway.filter.global;

import com.toast.gateway.config.GatewayJWTConfigProperties;
import com.toast.jwt.code.JWTResponseCode;
import com.toast.jwt.service.ITokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author 土司先生
 * @time 2023/3/23
 * @describe 全局过滤器配置类
 */
@Slf4j
@Component
public class JWTTokenCheckFilter implements GlobalFilter {
    @Autowired
    private GatewayJWTConfigProperties jwtConfig;
    @Autowired
    private ITokenService tokenService;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath(); // 获取路径
        if (this.jwtConfig.getSkipAuthUrls() != null && this.jwtConfig.getSkipAuthUrls().contains(url)) {
            return chain.filter(exchange); // 继续向下执行其他操作
        }
        // 网关将通过头信息获取到JWT的数据内容，网关技术通过WebFlux技术开发的
        String token = null;
        try {
            token = exchange.getRequest().getHeaders().get(this.jwtConfig.getHeaderName()).get(0);
        } catch (Exception e) {}
        log.info("网关Token检查，token = {}", token);
        // 如果token有误，直接响应，请求不会发送给目标的微服务
        ServerHttpResponse response = exchange.getResponse();
        if (StringUtils.isBlank(token)) { // 空 TOKEN
            DataBuffer buffer = response.bufferFactory().wrap(JWTResponseCode.NO_AUTH_CODE.toJSON().getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Flux.just(buffer)); // 异步响应错误
        } else { // TOKEN不为空，但有误
            if (this.tokenService.verifyToken(token)) { // 校验成功
                return chain.filter(exchange);
            } else {

            }DataBuffer buffer = response.bufferFactory().wrap(JWTResponseCode.TOKEN_TIMEOUT_CODE.toJSON().getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Flux.just(buffer)); // 异步响应错误
        }
    }
}
