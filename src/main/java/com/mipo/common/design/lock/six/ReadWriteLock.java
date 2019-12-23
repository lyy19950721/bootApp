package com.mipo.common.design.lock.six;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {

    /*我们刚刚接触到的ReentrantLock（排他锁）具有完全互斥排他的效果，即同一时刻只允许一个线程访问，
    这样做虽然虽然保证了实例变量的线程安全性，但效率非常低下。
    ReadWriteLock接口的实现类-ReentrantReadWriteLock读写锁就是为了解决这个问题。

    读写锁维护了两个锁，一个是读操作相关的锁也成为共享锁，一个是写操作相关的锁 也称为排他锁。
    通过分离读锁和写锁，其并发性比一般排他锁有了很大提升。

    多个读锁之间不互斥，读锁与写锁互斥，写锁与写锁互斥（只要出现写操作的过程就是互斥的。）。
    在没有线程Thread进行写入操作时，进行读取操作的多个Thread都可以获取读锁，
    而进行写入操作的Thread只有在获取写锁后才能进行写入操作。即多个Thread可以同时进行读取操作，
    但是同一时刻只允许一个Thread进行写入操作。*/


    public static void main(String[] args) {
        Test test = new Test();

        for (int i = 0; i < 2; i++) {
            new Thread(()->test.read()).start();
        }
    }

    static public class Test{
        private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        public void read() {
            try {
                try {
                    lock.readLock().lock();
                    System.out.println("获得读锁" + Thread.currentThread().getName()
                            + " " + System.currentTimeMillis());
                    Thread.sleep(10000);
                } finally {
                    lock.readLock().unlock();
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
