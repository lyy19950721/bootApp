package com.mipo.common.design.test;

public class Demo {

    public static void main(String[] args) {
        Person p = new Person("1");
        change(p);
        System.out.println(p.name);

        String a = new String("ab"); // a 为一个引用
        String b = new String("ab"); // b为另一个引用,对象的内容一样
        String aa = "ab"; // 放在常量池中
        String bb = "ab"; // 从常量池中查找
        if (aa == bb)
            System.out.println("aa==bb");
        if (a == b)
            System.out.println("a==b");
        if (a.equals(b))
            System.out.println("aEQb");
        if (42 == 42.0) {
            System.out.println("true");
        }


    }

    public static void change(Person p) {
        Person person = new Person("李四");
        p = person;
    }
    static class Person {
        String name;

        public Person(String name) {
            this.name = name;
        }

    }
}
