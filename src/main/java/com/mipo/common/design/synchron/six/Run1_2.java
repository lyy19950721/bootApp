package com.mipo.common.design.synchron.six;

public class Run1_2 {

    /*两个线程使用了不同的“对象监视器”,所以运行结果不是同步的了*/

    public static void main(String[] args) {
        Service service = new Service();
        MyObject object1 = new MyObject();
        MyObject object2 = new MyObject();

        ThreadA a = new ThreadA(service, object1);
        a.setName("a");
        a.start();

        ThreadB b = new ThreadB(service, object2);
        b.setName("b");
        b.start();
    }
}
