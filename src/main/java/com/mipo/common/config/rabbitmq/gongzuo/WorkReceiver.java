package com.mipo.common.config.rabbitmq.gongzuo;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@Slf4j

public class WorkReceiver {

    private final int instance;

    public WorkReceiver(int i) {
        this.instance = i;
    }
    @RabbitListener(queues = "work.hello")
    @RabbitHandler
    public void receive(String in) {
        StopWatch watch = new StopWatch();
        watch.start();
        log.info("instance {} [x] Received '{}'", this.instance, in);
        doWork(in);
        watch.stop();
        log.info("instance {} [x] Done in {}s", this.instance, watch.getTotalTimeSeconds());
    }

    private void doWork(String in) {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                ThreadUtil.sleep(1000);
            }
        }
    }
}
