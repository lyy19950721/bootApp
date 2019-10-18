package com.mipo.common.design.factory;

/**
 * @author ：lyy
 * @date ：Created in 2019-10-14 16:29
 * @description：抽象工厂类
 * @modified By：
 * @version: $
 */
public abstract class Creator {

    public abstract <T extends Product> T CreateProduct(Class<T> c);

}
