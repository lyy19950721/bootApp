package com.mipo.common.design.singleton;

/**
 * @author ：lyy
 * @date ：Created in 2019-10-14 16:19
 * @description：线程不安全
 * @modified By：
 * @version: $
 */
public class Singleton1 {

    private static Singleton1 singleton1 = null;

    private Singleton1() {}

    public static Singleton1 getSingleton1() {
        if (null == singleton1) {
            singleton1 = new Singleton1();
        }
        return singleton1;
    }
}
