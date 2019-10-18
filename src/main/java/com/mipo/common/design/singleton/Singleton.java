package com.mipo.common.design.singleton;

/**
 * @author ：lyy
 * @date ：Created in 2019-10-14 16:10
 * @description：单列模式通用代码
 * @modified By：
 * @version: $
 */
public class Singleton {

    /**
        单利模式定义：确保某一个类只有一个实例，而且自行实例化并向整个系统提供这个实例

        优点
        1 一个实例，减少内存开支，特别是一个对象需要频繁创建销毁
        2 避免对资源的多重占用
        使用场景
        1 要求生成唯一序列号的环境
        2 在项目需要一个共享访问点或共享数据。
        3 创建一个对象需要消耗的资源过多
     */
    private static final Singleton sinleton = new Singleton();

    private Singleton() {}

    public static Singleton getSinleton() {
        return sinleton;
    }
}
