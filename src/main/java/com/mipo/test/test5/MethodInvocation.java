package com.mipo.test.test5;

import java.lang.reflect.InvocationTargetException;

/**
 * @Classname MethodInvocation
 * @Description TODO
 * @Date 2021/1/4 15:41
 * @Created by li.yy
 */
public interface MethodInvocation {

    /**
     * 方法调用 每个通知的方法
     *
     * @return
     */
    Object process() throws InvocationTargetException, IllegalAccessException;

}
