package com.mipo.test.test5;

import java.lang.reflect.InvocationTargetException;

/**
 * @Classname AfterMethodInterceptor
 * @Description TODO
 * @Date 2021/1/4 15:40
 * @Created by li.yy
 */
public class AfterMethodInterceptor implements MethodInterceptor {

    public Object invoke(MethodInvocation methodInvocation) throws InvocationTargetException, IllegalAccessException {
        Object process = methodInvocation.process();
        System.out.println("后置通知");
        return process;
    }

}
