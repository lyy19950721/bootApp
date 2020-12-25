package com.mipo.test.test4;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Classname BigTest
 * @Description TODO
 * @Date 2020/11/25 16:20
 * @Created by li.yy
 */
public class BigTest {


    public static void main(String[] args) {
        List<User> a = new ArrayList<>();
        a.add(new User(1, "a"));
        a.add(new User(2, "b"));
        a.add(new User(3, "c"));
        a.add(new User(3, "c"));
        List<Integer> collect = a.stream().map(User::getId).distinct().collect(Collectors.toList());
        System.out.println(collect);


        int x,y;
        x = 5>>2;
        y = x>>>2;
        System.out.println(y);
//        List<User> b = new ArrayList<>();
//        b.add(new User(4, "d"));
//        b.add(new User(5, "e"));
//        b.add(new User(1, "f"));
//        System.out.println(BigTest.getList(a, b));
    }

    // 方法
    private static List<User> getList(List<User> a, List<User> b) {
        HashMap<Integer, User> map = new HashMap<>();
        for (User user : b) {
            map.put(user.getId(), user);
        }
        // b为准
        a.removeIf(t-> t.getId().equals(2));
        a.removeIf(next -> map.containsKey(next.getId()));
        System.out.println(a);
        System.out.println(b);
        a.addAll(b);
        return a;
    }

    @Data
    @AllArgsConstructor
    private static class User{
        private Integer id;

        private String name;


    }


}
