package com.mipo.common.design.factory;

/**
 * @author ：lyy
 * @date ：Created in 2019-10-14 16:32
 * @description：具体工厂类
 * @modified By：
 * @version: $
 */
public class ConcreateCreator extends Creator {
    @Override
    public <T extends Product> T CreateProduct(Class<T> c) {
        Product product = null;

        try {
            product = (Product) Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T)product;
    }
}
