package com.mipo.common.design.synchron.seven;

public class ThreadA extends Thread {
    private Service service;
    public ThreadA(Service service) {
        super();
        this.service = service;
    }
    @Override
    public void run() {
        service.printA();
    }
}
