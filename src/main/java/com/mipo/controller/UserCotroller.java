package com.mipo.controller;

import com.mipo.pojo.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：lyy
 * @date ：Created in 2019-08-26 11:22
 * @description：登陆
 * @modified By：
 * @version: $
 */
@Controller
@RequestMapping(value = "/login")
public class UserCotroller {

    @ResponseBody
    @RequestMapping(value = "/toLogin", method = RequestMethod.POST)
    public String toLogin(@Validated @RequestBody UserDTO userDTO) {
        System.out.println(userDTO.toString());
        return "index";
    }
}
