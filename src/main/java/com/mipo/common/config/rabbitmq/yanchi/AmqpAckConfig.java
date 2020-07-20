package com.mipo.common.config.rabbitmq.yanchi;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Classname AmqpAckConfig
 * @Description TODO
 * @Date 2020/7/15 10:14
 * @Created by li.yy
 */
@Component
public class AmqpAckConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("return--message:" + new String(message.getBody()) + ",replyCode:" + replyCode
                + ",replyText:" + replyText + ",exchange:" + exchange + ",routingKey:" + routingKey);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
        } else {
            System.out.println("消息发送确认失败:" + cause);
        }
    }
}

