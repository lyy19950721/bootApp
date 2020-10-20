package com.mipo.test.test1;

/**
 * @Classname PutInEden
 * @Description TODO
 * @Date 2020/9/11 15:01
 * @Created by li.yy
 */
public class PutInEden {
    public static void main(String[] args){
        // 如何将新对象预留在年轻代
        // 如何让大对象进入年老代
        //  JVM 参数-XX:+PrintGCDetails -Xmx20M -Xms20M
        byte[] b1,b2,b3,b4;//定义变量
        b1=new byte[1024*1024];//分配 1MB 堆空间，考察堆空间的使用情况
        b2=new byte[1024*1024];
        b3=new byte[1024*1024];
        b4=new byte[1024*1024];
    }
}
