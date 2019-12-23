package com.mipo.common.design.ThreadLocal;

import java.util.Date;

/**
 */
public class Test5 {

    /*在使用InheritableThreadLocal类需要注意的一点是：如果子线程在取得值的同时，
    主线程将InheritableThreadLocal中的值进行更改，那么子线程取到的还是旧值*/

    public static void main(String[] args) {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("       在Main线程中取值=" + Tools.tl.get());
                Thread.sleep(100);
            }
            Thread.sleep(5000);
            ThreadA a = new ThreadA();
            a.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static public class Tools {
        public static InheritableThreadLocalExt tl = new InheritableThreadLocalExt();
    }
    static public class InheritableThreadLocalExt extends InheritableThreadLocal {
        @Override
        protected Object initialValue() {
            return new Date().getTime();
        }

        @Override
        protected Object childValue(Object parentValue) {
            return parentValue + " 我在子线程加的~!";
        }
    }


    static public class ThreadA extends Thread {

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("在ThreadA线程中取值=" + Tools.tl.get());
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
