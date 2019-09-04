package com.mipo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ：lyy
 * @date ：Created in 2019-08-16 11:05
 * @description：
 * @modified By：
 * @version: $
 */
@Controller
public class LogController {

    @RequestMapping(value = "index")
    public String index() {
        return "login";
    }
}
