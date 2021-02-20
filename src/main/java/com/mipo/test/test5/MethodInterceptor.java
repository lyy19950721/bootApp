package com.mipo.test.test5;

import java.lang.reflect.InvocationTargetException;

/**
 * @Classname MethodInterceptor
 * @Description TODO
 * @Date 2021/1/4 15:39
 * @Created by li.yy
 */
public interface MethodInterceptor {

    /**
     * 执行通知的拦截
     *
     * @param methodInvocation
     * @return
     */
    Object invoke(MethodInvocation methodInvocation) throws InvocationTargetException, IllegalAccessException;

}
