package com.mipo.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestTask {

    @Scheduled(cron = "0 0/7 * * * ?")
    public void test1(){
        log.info("[test1]当前线程{}", Thread.currentThread().getName());
    }

    @Scheduled(cron = "0 0/7 * * * ?")
    public void test2(){
        log.info("[test2]当前线程{}", Thread.currentThread().getName());
    }
}
