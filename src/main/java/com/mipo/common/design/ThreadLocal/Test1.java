package com.mipo.common.design.ThreadLocal;

public class Test1 {
    public static ThreadLocal<String> t1 = new ThreadLocal<>();

    public static void main(String[] args) {
        if (t1.get() == null) {
            System.out.println("为ThreadLocal类对象放入值:aaa");
            t1.set("aaaֵ");
        }
        System.out.println(t1.get());//aaa
        System.out.println(t1.get());//aaa
    }

}


