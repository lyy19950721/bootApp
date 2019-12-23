package com.mipo.common.design.synchron.two;

public class Run {

    /*synchronized取得的锁都是对象锁，而不是把一段代码或方法当做锁。
    如果多个线程访问的是同一个对象，哪个线程先执行带synchronized关键字的方法，
    则哪个线程就持有该方法，那么其他线程只能呈等待状态。如果多个线程访问的是多个对象则不一定，因为多个对象会产生多个锁

    那么我们思考一下当多个线程访问的是同一个对象中的非synchronized类型方法会是什么效果？
    答案是：会异步调用非synchronized类型方法，解决办法也很简单在非synchronized类型方法前加上synchronized关键字即可.*/
    public static void main(String[] args) {
        try {
            PublicVar publicVarRef = new PublicVar();
            ThreadA thread = new ThreadA(publicVarRef);
            thread.start();

            Thread.sleep(200);//打印结果受此值大小影响

            publicVarRef.getValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
