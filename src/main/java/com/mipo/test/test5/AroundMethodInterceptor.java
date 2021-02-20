package com.mipo.test.test5;

import java.lang.reflect.InvocationTargetException;

/**
 * @Classname AroundMethodInterceptor
 * @Description TODO
 * @Date 2021/1/4 15:41
 * @Created by li.yy
 */
public class AroundMethodInterceptor implements MethodInterceptor  {

    public Object invoke(MethodInvocation methodInvocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("环绕通知之前执行....");
        Object process = methodInvocation.process();
        System.out.println("环绕通知之后执行....");
        return process;
    }

}
