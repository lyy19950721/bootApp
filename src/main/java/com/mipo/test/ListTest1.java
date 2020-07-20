package com.mipo.test;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Classname ListTest1
 * @Description TODO
 * @Date 2020/7/4 19:49
 * @Created by li.yy
 */
public class ListTest1 {

    public static void main(String[] args) {
        List<Student> list1 = new ArrayList<>();
        list1.add(new Student("1","a"));
        list1.add(new Student("2","b"));
        list1.add(new Student("3","c"));
        list1.add(new Student("4","d"));
        list1.add(new Student("5","e"));
        List<Student> list2 = new ArrayList<>();
        list2.add(new Student("6","d"));
        list2.add(new Student("7","c"));
        list2.add(new Student("8","g"));
        list2.add(new Student("9","h"));
        list2.add(new Student("10","j"));
        List<Student> yu = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {
                if(list1.get(i).getPath().equals(list2.get(j).getPath())) {
                    yu.add(new Student(list1.get(i).getNo(),list1.get(i).getPath()));
                }
            }
        }

        List<Student> collect = list1.stream().filter(new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                for (Student student1 : list2) {
                    if (student1.getPath().equals(student.getPath())) {
                        return false;
                    }
                }
                return true;
            }
        }).collect(Collectors.toList());

        System.out.println(collect.toString());
    }

    @Data
    static class Student {

        public Student(String no, String path) {
            this.no = no;
            this.path = path;
        }

        private String no;

        private String path;
    }
}
