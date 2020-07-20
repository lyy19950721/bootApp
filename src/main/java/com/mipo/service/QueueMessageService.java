package com.mipo.service;

/**
 * @Classname QueueMessageService
 * @Description TODO
 * @Date 2020/7/15 10:15
 * @Created by li.yy
 */
public interface QueueMessageService {

    /**
     * 发送正常队列消息
     *
     * @author lc
     * @date 2020/7/1 11:26
     */
    void send(String exchangeKey, String routingKey, Object message);


    /**
     * 发送延时队列消息
     *
     * @param
     * @author lc
     * @date 2020/6/30 11:47
     */
    void delayedSend(String exchangeKey, String routingKey, Object message, int msec);

}
