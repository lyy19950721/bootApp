package com.mipo.common.design.vola;

public class Run {


   /* volatile 修饰的成员变量在每次被线程访问时，都强迫从主存（共享内存）中重读该成员变量的值。
    而且，当成员变量发生变化时，强迫线程将变化值回写到主存（共享内存）。这样在任何时刻，
    两个不同的线程总是看到某个成员变量的同一个值，这样也就保证了同步数据的可见性。*/


    /*synchronized关键字和volatile关键字比较
    volatile关键字是线程同步的轻量级实现，所以volatile性能肯定比synchronized关键字要好。
    但是volatile关键字只能用于变量而synchronized关键字可以修饰方法以及代码块。
    synchronized关键字在JavaSE1.6之后进行了主要包括为了减少获得锁和释放锁带来的性能消耗而引入的
    偏向锁和轻量级锁以及其它各种优化之后执行效率有了显著提升，实际开发中使用synchronized关键字还是更多一些。
    多线程访问volatile关键字不会发生阻塞，而synchronized关键字可能会发生阻塞
    volatile关键字能保证数据的可见性，但不能保证数据的原子性。synchronized关键字两者都能保证。
    volatile关键字用于解决变量在多个线程之间的可见性，而synchronized关键字解决的是多个线程之间访问资源的同步性。*/

    public static void main(String[] args) throws Exception {
        /*RunThread thread = new RunThread();
        thread.start();
        Thread.sleep(1000);
        thread.setRunning(false);
        System.out.println("赋值成功");*/

        MyThread[] mythreadArray = new MyThread[100];
        for (int i = 0; i < 100; i++) {
            mythreadArray[i] = new MyThread();
        }

        for (int i = 0; i < 100; i++) {
            mythreadArray[i].start();
        }

    }
}
