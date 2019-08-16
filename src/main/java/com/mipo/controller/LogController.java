package com.mipo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：lyy
 * @date ：Created in 2019-08-16 11:05
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping(value = "log")
public class LogController {

    @RequestMapping(value = "getLog")
    public String getLog() {
        return "this good boy";
    }
}
