package com.mipo.common.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import javax.annotation.Resource;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
public class ScheduleConfig implements SchedulingConfigurer {

    @Resource(name = "asyncExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar
                .setScheduler(Executors.newScheduledThreadPool(5, threadPoolTaskExecutor));
        //scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(5));
    }
}
