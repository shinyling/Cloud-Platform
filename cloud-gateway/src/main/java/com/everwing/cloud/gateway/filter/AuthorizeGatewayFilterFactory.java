package com.everwing.cloud.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.everwing.cloud.common.entity.Account;
import com.everwing.cloud.common.entity.ResultJson;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author DELL shiny
 * @create 2019/5/16
 */
@Component
public class AuthorizeGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthorizeGatewayFilterFactory.Config> {

    private static final Logger logger= LoggerFactory.getLogger(AuthorizeGatewayFilterFactory.class);

    private static final String AUTHORIZE_TOKEN = "token";

    @Autowired
    private RedisTemplate redisTemplate;

    public AuthorizeGatewayFilterFactory() {
        super(Config.class);
        logger.info("Loaded GatewayFilterFactory [Authorize]");
    }


    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!config.isEnabled()) {
                return chain.filter(exchange);
            }

            ServerHttpRequest request = exchange.getRequest();
            HttpHeaders headers = request.getHeaders();
            String token = headers.getFirst(AUTHORIZE_TOKEN);

            ServerHttpResponse response = exchange.getResponse();
            if (StringUtils.isEmpty(token)) {
                logger.error("未携带身份标识");
                return returnJson(response,"请携带身份标识！");
            }
            Account account = (Account) redisTemplate.opsForValue().get(token);
            if (account == null) {
                logger.error("未登录或登录已过期");
                return returnJson(response,"未登录或登录已过期！");
            }
            logger.info("延长用户active时间:{}",account);
            redisTemplate.expire(token,20, TimeUnit.MINUTES);
            return chain.filter(exchange);
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("enabled");
    }

    public static class Config {
        /**
         * 控制是否开启认证
         */
        private boolean enabled;

        public Config() {}

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    public Mono<Void> returnJson(ServerHttpResponse response,String msg){
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        ResultJson<Object> resultJson = new ResultJson();
        resultJson.setCode("401");
        resultJson.setMessage(msg);
        byte[] datas = JSON.toJSONString(resultJson).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(datas);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }
}
