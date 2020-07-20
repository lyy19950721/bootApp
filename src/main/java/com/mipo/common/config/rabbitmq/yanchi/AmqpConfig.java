package com.mipo.common.config.rabbitmq.yanchi;

/**
 * @Classname AmqpConfig
 * @Description TODO
 * @Date 2020/7/15 10:13
 * @Created by li.yy
 */
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * rabbit配置类（声明交换机、队列以及他们的绑定关系）
 *
 * @author lc
 * @date 2020/7/1 11:15
 */
@Configuration
public class AmqpConfig {

    // 交换机名称
    public static final String TEST_EXCHANGE_KEY = "exchange.pay";

    // 队列名称（测试）
    public static final String TEST_QUEUE_KEY = "test.pay";

    // 队列路线/绑定关系（测试）
    public static final String TEST_ROUTK = "test.pay";


    @Bean
    public Queue testQueue() {

        return new Queue(TEST_QUEUE_KEY, true);
    }


    /**
     * 延时队列交换器
     *
     * @author lc
     * @date 2020/6/30 15:06
     */
    @Bean
    public CustomExchange testExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(TEST_EXCHANGE_KEY, "x-delayed-message", true, false, args);
    }


    @Bean
    public Binding testBinding(CustomExchange testExchange, Queue testQueue) {
        Binding binding = BindingBuilder.bind(testQueue).to(testExchange).with(TEST_ROUTK).noargs();
        return binding;
    }
}

