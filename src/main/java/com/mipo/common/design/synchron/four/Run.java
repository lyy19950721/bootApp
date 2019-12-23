package com.mipo.common.design.synchron.four;

public class Run {

    /*说明当存在父子类继承关系时，子类是完全可以通过“可重入锁”调用父类的同步方法。
    另外出现异常时，其锁持有的锁会自动释放

    同步不具有继承性
    如果父类有一个带synchronized关键字的方法，子类继承并重写了这个方法。
    但是同步不能继承，所以还是需要在子类方法中添加synchronized关键字*/

    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
    }
}
