package com.mipo.test;

/**
 * @author ：lyy
 * @date ：Created in 2019-08-23 14:55
 * @description：
 * @modified By：
 * @version: $
 */
public class SynchronizedTest {

    public synchronized void doSth(){
        System.out.println("Hello World");
    }

    public void doSth1(){
        synchronized (SynchronizedTest.class){
            System.out.println("Hello World");
        }
    }
}
