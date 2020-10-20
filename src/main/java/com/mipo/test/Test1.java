package com.mipo.test;

import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import sun.misc.Launcher;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * @Classname Test1
 * @Description TODO
 * @Date 2020/7/31 16:39
 * @Created by li.yy
 */
public class Test1 {

    public static void main(String[] args) {
        String format = String.format("%05d", 123245);
        BigDecimal str = new BigDecimal("120.1001");
        //System.out.println(str.setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros());
        //String str = new BigDecimal("120.10").stripTrailingZeros().toPlainString();
        //System.out.println(str);
        //System.out.println(format);

        //IntStream.range(0,3).forEach(System.out::println);
        //Launcher
        HashSet set = new HashSet();
        set.add(new Student("123","tom"));
        set.add(new Student("123","tom"));
        set.add(new String("2"));
        set.add(new String("2"));
        System.out.println(set.size());
        System.out.println(new Student("123","tom").hashCode());
        System.out.println(new Student("123","tom").hashCode());
        //ClassLoader.getSystemClassLoader().loadClass();
        Object o = new Object();
        System.out.println(o.getClass().getClassLoader());

        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;
        StringBuilder sb = new StringBuilder();
sb.append("2");
        String s5 = "a" + "b";
        System.out.println("===================================");
        System.out.println(s3 == s4);
        System.out.println(s3 == s5);
        //System.out.println(new Student("123","tom") == new Student("123","tom"));
//       HashMap<Student,Integer> map = new HashMap<>(1000);
//       int counter=0;
//       while (true) {
//           Student student = new Student("321","123");
//           map.put(student,1);
//           counter++;
//           if(counter%1000 == 0) {
//               System.out.println("map size:" + map.size());
//               System.out.println(Runtime.getRuntime().freeMemory()/(1024*1024));
//           }
//       }
    }


}
//@Data
//@AllArgsConstructor
        //@EqualsAndHashCode
class Student {
    private String uuid;
    private String name;

    public Student(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }
}