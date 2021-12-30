package com.xinxin.apigateway.filter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.netflix.discovery.converters.Auto;
import com.xinxin.apigateway.constant.RedisConstant;
import com.xinxin.apigateway.util.CookieUtil;
import io.micrometer.core.instrument.util.JsonUtils;
import io.micrometer.core.instrument.util.StringUtils;
import jdk.nashorn.internal.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * 鉴权过滤器简单例子
 *
 */
public class SimpleAuthFilter implements GlobalFilter, Ordered {
    Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = String.valueOf(exchange.getRequest().getPath().pathWithinApplication().value());
        logger.info("++++" + exchange.getRequest().getPath()); // ++++/order/order/create
        logger.info("++++" + exchange.getRequest().getPath().pathWithinApplication().value());
        logger.info("+++uri"); // +++http://localhost:8088/order/order/create
        logger.info(String.valueOf(exchange.getRequest().getCookies()));

        /**
         * postman测试请求中需要设置Cookie openid=abc
         * 模拟买家登录，否则提示401
         */
        if ("/order/order/create".equals(path)) {
            logger.info("***创建订单");
            String openid = CookieUtil.getHttpCookie(exchange.getRequest(), "openid");
            if (StringUtils.isBlank(openid)) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }

        /**
         * postman测试请求中需要设置Cookie token=7ecd549e-5c9f-4e3d-b923-588ea68d6659
         * 模拟卖家登录，否则提示401
         */
        if ("/order/order/finish".equals(path)) {
            logger.info("***完结订单");
            String token = CookieUtil.getHttpCookie(exchange.getRequest(), "token");
            if (StringUtils.isEmpty(token)
                    || StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, token)))) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -99;
    }
}
