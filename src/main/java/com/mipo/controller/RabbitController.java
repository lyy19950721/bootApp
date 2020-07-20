package com.mipo.controller;


import cn.hutool.core.thread.ThreadUtil;
import com.mipo.common.config.rabbitmq.gongzuo.WorkSender;
import com.mipo.common.config.rabbitmq.jiandan.SimpleSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "RabbitController", description = "RabbitMQ功能测试")
@Controller
@RequestMapping("/rabbit")
public class RabbitController {

    @Autowired
    private SimpleSender simpleSender;
    @Autowired
    private WorkSender workSender;

    @ApiOperation("简单模式")
    @RequestMapping(value = "/simple", method = RequestMethod.GET)
    @ResponseBody
    public String simpleTest() {
        for(int i=0;i<10;i++){
            simpleSender.send();
            ThreadUtil.sleep(1000);
        }
        return "Success";
    }

    @ApiOperation("工作模式")
    @RequestMapping(value = "/work", method = RequestMethod.GET)
    @ResponseBody
    public String workTest() {
        for(int i=0;i<10;i++){
            workSender.send(i);
            ThreadUtil.sleep(1000);
        }
        return "Success";
    }
}
