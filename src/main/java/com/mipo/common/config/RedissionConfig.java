package com.mipo.common.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.redisson.config.Config;
/**
 * @Classname RedissionConfig
 * @Description TODO
 * @Date 2020/11/27 10:25
 * @Created by li.yy
 */
@Slf4j
@Configuration
public class RedissionConfig {

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public RedissonClient redissonClient() {
        RedissonClient redissonClient;

        Config config = new Config();
        String url = "redis://" + redisProperties.getHost() + ":" + redisProperties.getPort();
        config.useSingleServer().setAddress(url)
                .setPassword(redisProperties.getPassword())
                .setDatabase(redisProperties.getDatabase());

        try {
            redissonClient = Redisson.create(config);
            return redissonClient;
        } catch (Exception e) {
            log.error("RedissonClient init redis url:[{}], Exception:", url, e);
            return null;
        }
    }
}
