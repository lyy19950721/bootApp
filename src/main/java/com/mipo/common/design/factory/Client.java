package com.mipo.common.design.factory;

/**
 * @author ：lyy
 * @date ：Created in 2019-10-14 16:34
 * @description： 场景类
 * @modified By：
 * @version: $
 */
public class Client {
    public static void main(String[] args) {
        Creator creator = new ConcreateCreator();
        Product product = creator.CreateProduct(ConcreteProduct1.class);
        product.method2();
    }
}
