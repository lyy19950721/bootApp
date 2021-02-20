package com.mipo.test.test5;

import java.lang.reflect.InvocationTargetException;

/**
 * @Classname BeforMethodInterceptor
 * @Description TODO
 * @Date 2021/1/4 15:39
 * @Created by li.yy
 */
public class BeforMethodInterceptor implements MethodInterceptor{

    public Object invoke(MethodInvocation methodInvocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("前置通知...");
        return methodInvocation.process();// 目标方法
    }

}
