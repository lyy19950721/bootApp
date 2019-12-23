package com.mipo.common.design.synchron.three;

public class MyThread extends Thread {
    @Override
    public void run() {
        Service service = new Service();
        service.service1();
    }

}
