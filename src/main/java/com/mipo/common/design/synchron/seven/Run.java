package com.mipo.common.design.synchron.seven;

public class Run {

    /*静态同步synchronized方法与synchronized(class)代码块持有的锁一样，都是Class锁，
    Class锁对对象的所有实例起作用。synchronized关键字加到非static静态方法上持有的是对象锁。
    线程A,B和线程C持有的锁不一样，所以A和B运行同步，但是和C运行不同步*/


    public static void main(String[] args) {
        Service service = new Service();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();

        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();

        ThreadC c = new ThreadC(service);
        c.setName("C");
        c.start();


        //字符串常量池中的字符串只存在一份！ 即执行完第一行代码后，常量池中已存在 “a”，
        // 那么s2不会在常量池中申请新的空间，而是直接把已存在的字符串内存地址返回给s2
        String s1 = "a";
        String s2 = "a";
        System.out.println(s1 == s2);
    }
}
