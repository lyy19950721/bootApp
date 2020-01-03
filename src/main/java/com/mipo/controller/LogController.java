package com.mipo.controller;

import com.mipo.common.page.PageUtil;
import com.mipo.entity.User;
import com.mipo.pojo.dto.BaseDTO;
import com.mipo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    /*@PostMapping(value = "listUser")
    public PageBean<User> listUser(@RequestBody @Validated BaseDTO baseDTO) {
        List<User> users = userService.listUser(baseDTO, true);
        return new PageBean<>(users);
    }*/

    @RequestMapping("/list")
    public String list(BaseDTO baseDTO, Model model) {
        List<User> users=userService.listUser(baseDTO, false);
        model.addAttribute("pageInfo", new PageUtil<>(baseDTO.getPageNum(),baseDTO.getPageSize(), users));
        return "index";
    }

}
