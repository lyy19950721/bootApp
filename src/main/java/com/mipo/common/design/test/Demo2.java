package com.mipo.common.design.test;

import io.swagger.models.auth.In;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo2 {

    private int i;



    public static void main(String[] args) {

        Demo2.call(new Handler<Object>() {
            @Override
            public Object execute(String str) {
                System.out.println(str);
                return null;
            }
        });

        Demo2.call(t -> {
            System.out.println(t);
            return null;
        });

        List list = Arrays.asList("1", "2", "3", "4", "5");

        list.stream().forEach(t-> System.out.println());


        Map map = new HashMap();
        map.put(1, new Demo());
        map.put(2, new Demo());
        map.put(3, new Demo());
    }

    public static  <T> T call(Handler<T> handler) {
        return handler.execute("123");
    }
}
