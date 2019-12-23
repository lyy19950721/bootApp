package com.mipo.common.design.synchron.six;

public class Run1_1 {

    /*两个线程使用了同一个“对象监视器”,所以运行结果是同步的*/

    public static void main(String[] args) {
        Service service = new Service();
        MyObject object = new MyObject();

        ThreadA a = new ThreadA(service, object);
        a.setName("a");
        a.start();

        ThreadB b = new ThreadB(service, object);
        b.setName("b");
        b.start();
    }
}
