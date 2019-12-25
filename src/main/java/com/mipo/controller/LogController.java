package com.mipo.controller;

import com.mipo.common.page.PageBean;
import com.mipo.entity.User;
import com.mipo.pojo.dto.BaseDTO;
import com.mipo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：lyy
 * @date ：Created in 2019-08-16 11:05
 * @description：
 * @modified By：
 * @version: $
 */
@Controller
public class LogController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "index")
    public String index() {
        return "login";
    }

    @PostMapping(value = "listUser")
    @ResponseBody
    public PageBean<User> listUser(@RequestBody @Validated BaseDTO baseDTO) {
        List<User> users = userService.listUser(baseDTO, true);
        return new PageBean<>(users);
    }
}
