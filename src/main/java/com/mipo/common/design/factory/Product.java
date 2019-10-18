package com.mipo.common.design.factory;

/**
 * @author ：lyy
 * @date ：Created in 2019-10-14 16:23
 * @description：抽象产品类  工厂模式
 * @modified By：
 * @version: $
 */
public abstract class Product {
    /**
     工厂模式定义： 定义一个用于创建对象的接口，让子类决定实例化哪一个类。工厂方法使一个类的
     实例化延迟到其子类子类

     优点 良好的封装性，代码结构清晰
     */

    // 通用方法
    public void method1() {}

    public abstract void method2();
}
