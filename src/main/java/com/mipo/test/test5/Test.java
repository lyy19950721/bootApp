package com.mipo.test.test5;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2021/1/4 15:44
 * @Created by li.yy
 */
public class Test {

    public static void main(String[] args) throws Exception {
        List<MethodInterceptor> methodInterceptors = new ArrayList<>();
        methodInterceptors.add(new BeforMethodInterceptor());
        methodInterceptors.add(new AfterMethodInterceptor());
        methodInterceptors.add(new AroundMethodInterceptor());
        UserService userService = new UserService();
        Method method = UserService.class.getMethod("login", String.class, String.class);
        Object [] objects = {"xiaoming", "12"};
        new DefaultMethodInvacation(methodInterceptors, userService, method, objects).process();
    }
}
