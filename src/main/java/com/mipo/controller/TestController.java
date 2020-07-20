package com.mipo.controller;

/**
 * @Classname TestController
 * @Description TODO
 * @Date 2020/7/15 10:15
 * @Created by li.yy
 */
import com.mipo.common.config.rabbitmq.yanchi.AmqpConfig;
import com.mipo.service.QueueMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private QueueMessageService queueMessageService;

    @GetMapping("/do")
    public void dos(String msg, int msec) {
        log.info("开始发送延时队列。。。。。。。。。。。。。");

        log.info(new Date().toString() + "----------当前时间");
        HashMap<String, Object> param = new HashMap<>();
        param.put("params", 1);
        param.put("order", 2);

        //queueMessageService.send(AmqpConfig.IMPORT_EXCHANGE_KEY, AmqpConfig.TEST_1_QUEUE_KEY, JSON.toJSONString(param));

        queueMessageService.delayedSend(AmqpConfig.TEST_EXCHANGE_KEY, AmqpConfig.TEST_QUEUE_KEY, msg, msec);
    }
}


