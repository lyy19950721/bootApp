package com.mipo.common.design.lock.six;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock1 {

    /*运行两个使用同一个Service对象实例的线程a,b，线程a执行上面的read方法，
    线程b执行上面的write方法。你会发现同一时间只允许一个线程执行lock()方法后面的代码。
    记住：只要出现写操作的过程就是互斥的*/

    public static void main(String[] args) {

        Service service = new Service();

        new Thread(()->service.read()).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->service.write()).start();



    }

    static public class Service{
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
                e.printStackTrace();
            }
        }

        public void write() {
            try {
                try {
                    lock.writeLock().lock();
                    System.out.println("获得写锁" + Thread.currentThread().getName()
                            + " " + System.currentTimeMillis());
                    Thread.sleep(10000);
                } finally {
                    lock.writeLock().unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
