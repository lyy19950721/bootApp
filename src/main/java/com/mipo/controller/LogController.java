package com.mipo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ：lyy
 * @date ：Created in 2019-08-16 11:05
 * @description：
 * @modified By：
 * @version: $
 */
//@RestController
@Controller
@RequestMapping(value = "log")
public class LogController {

    @RequestMapping(value = "index")
    public String index(Model model) {
        model.addAttribute("name","tom");
        System.out.println(123);
        return "index";
    }
}
