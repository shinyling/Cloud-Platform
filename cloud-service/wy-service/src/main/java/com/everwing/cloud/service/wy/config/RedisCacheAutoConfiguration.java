package com.everwing.cloud.service.wy.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @author DELL shiny
 * @create 2019/5/16
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisCacheAutoConfiguration {

    @Bean

    public RedisTemplate<String, Serializable> redisCacheTemplate(LettuceConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, Serializable> template = new RedisTemplate<>();

        //键的序列化方式
        template.setKeySerializer(new StringRedisSerializer());

        //值的序列化方式
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        template.setConnectionFactory(redisConnectionFactory);

        return template;
    }
}
