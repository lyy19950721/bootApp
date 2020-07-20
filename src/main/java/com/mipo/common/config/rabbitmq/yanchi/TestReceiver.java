package com.mipo.common.config.rabbitmq.yanchi;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * @Classname TestReceiver
 * @Description TODO
 * @Date 2020/7/15 10:17
 * @Created by li.yy
 */
@Slf4j
@Component
@EnableRabbit
public class TestReceiver {


    @RabbitListener(queues = AmqpConfig.TEST_QUEUE_KEY)
    public void process(String msg, Channel channel, Message message) {
        log.info("======================延时队列开始执行。。。。。。。。。。。。。。");
        log.info(new Date().toString() + ",延时收到了信息 message = " + msg);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

