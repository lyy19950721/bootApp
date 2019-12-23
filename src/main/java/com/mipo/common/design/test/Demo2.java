package com.mipo.common.design.test;

import com.alibaba.fastjson.JSON;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.Map;

public class Demo2 {

    public static void main(String[] args) {

        Map map = new HashMap();

        map.put("uid", 1l);
        map.put("activityId", 2l);

        String s = JSON.toJSONString(map);

    }
}
