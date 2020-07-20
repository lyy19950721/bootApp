package com.mipo.common.config.rabbitmq.jiandan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

@Slf4j
public class SimpleSender {

    @Autowired
    private RabbitTemplate template;

    private static final String queueName="simple.hello";

    public void send() {
        String message = "Hello World!";
        this.template.convertAndSend(queueName, message);
        log.info(" [x] Sent '{}'", message);
    }

    @Async("asyncExecutor")
     public void Test() {
        System.out.println(Thread.currentThread().getId());
    }


}
