package com.mipo.test;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @Classname SyncThread
 * @Description TODO
 * @Date 2020/8/31 11:44
 * @Created by li.yy
 */
@Slf4j
public class SyncThread implements Runnable{

    private Object object = new Object();
    /**
     * synchronized 修饰非静态方法
     */
    private void sync5() {
        //System.out.println(Thread.currentThread().getName()+1);
        synchronized (this) {
            try {
                System.out.println(Thread.currentThread().getName()+2);
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()+3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static synchronized void sync4() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.sync4();
    }

    public static void main(String[] args) {
        SyncThread syncThread = new SyncThread();
        Thread F_thread1 = new Thread(new SyncThread(), "F_thread1");
        Thread F_thread2 = new Thread(new SyncThread(), "F_thread2");
        Thread F_thread3 = new Thread(syncThread, "F_thread3");
        Thread F_thread4 = new Thread(syncThread, "F_thread4");
        F_thread1.start();
        F_thread2.start();
        F_thread3.start();
        F_thread4.start();
        StringBuilder sb = new StringBuilder();
        sb.append(123);
    }
}
