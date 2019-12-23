package com.mipo.common.design.wait;

public class Run {


    /*等待/通知机制，是指一个线程A调用了对象O的wait()方法进入等待状态，
    而另一个线程B调用了对象O的notify()/notifyAll()方法，线程A收到通知后退出等待队列，
    进入可运行状态，进而执行后续操作。上诉两个线程通过对象O来完成交互，而对象上的wait()方法
    和notify()/notifyAll()方法的关系就如同开关信号一样，用来完成等待方和通知方之间的交互工作。*/

    /*synchronized关键字可以将任何一个Object对象作为同步对象来看待，
    而Java为每个Object都实现了等待/通知（wait/notify）机制的相关方法，
    它们必须用在synchronized关键字同步的Object的临界区内。
    通过调用wait()方法可以使处于临界区内的线程进入等待状态，同时释放被同步对象的锁。
    而notify()方法可以唤醒一个因调用wait操作而处于阻塞状态中的线程，使其进入就绪状态。
    被重新唤醒的线程会视图重新获得临界区的控制权也就是锁，并继续执行wait方法之后的代码。
    如果发出notify操作时没有处于阻塞状态中的线程，那么该命令会被忽略*/

   /* 当方法wait()被执行后，锁自动被释放，但执行玩notify()方法后，锁不会自动释放。
    必须执行完notify()方法所在的synchronized代码块后才释放。*/

    public static void main(String[] args) {

        try {
            Object lock = new Object();

            ThreadA a = new ThreadA(lock);
            a.start();

            Thread.sleep(50);

            ThreadB b = new ThreadB(lock);
            b.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

