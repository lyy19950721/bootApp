package com.mipo.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Classname RedisProperties
 * @Description TODO
 * @Date 2020/11/27 10:26
 * @Created by li.yy
 */

@Data
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {

    private String host;
    private int port;
    private String password;
    private int database;
}
