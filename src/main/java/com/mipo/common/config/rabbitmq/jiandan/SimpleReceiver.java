package com.mipo.common.config.rabbitmq.jiandan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Slf4j
public class SimpleReceiver {

    @RabbitHandler
    @RabbitListener(queues = "simple.hello")
    public void receive(Message in) {

        String messageBody = new String(in.getBody());
        log.info(" [x] Received '{}'", messageBody);
    }
}
