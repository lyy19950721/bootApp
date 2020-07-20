package com.mipo.service.imlp;

/**
 * @Classname QueueMessageServiceImpl
 * @Description TODO
 * @Date 2020/7/15 10:15
 * @Created by li.yy
 */
import com.mipo.service.QueueMessageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import java.util.UUID;

@Service
public class QueueMessageServiceImpl implements QueueMessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(String exchangeKey, String routingKey, Object message) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(exchangeKey, routingKey, message, correlationData);
    }


    @Override
    public void delayedSend(String exchangeKey, String routingKey, Object msg,final int xdelay) {

        rabbitTemplate.convertAndSend(exchangeKey, routingKey, msg, message -> {
            // 设置延迟时间
            message.getMessageProperties().setDelay(xdelay);
            return message;
        });
    }

}
