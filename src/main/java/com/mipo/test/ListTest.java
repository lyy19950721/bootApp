package com.mipo.test;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ListTest {

    public static void main(String[] args) throws Exception{

        StringBuffer sb = new StringBuffer();
        sb.append("abcdefg");
        System.out.println(sb.replace(0,1,""));


        List<Integer> list = new ArrayList();

        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);

        /**集合分割*/
        List<List<Integer>> partition = Lists.partition(list, 3);
        List<Callable<Integer>> tasks = new ArrayList<>();
        // 并发执行任务
        for (List<Integer> integers : partition) {
            System.out.println(integers.toString());
            tasks.add(()-> {
                System.out.println("/??"+integers.get(0));
                return integers.get(0);
            });
        }

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        // 执行
        List<Future<Integer>> futures = executorService.invokeAll(tasks);
        // 拿到任务结果
        for (Future<Integer> future : futures) {
            Integer integer = future.get();
            System.out.println(integer);
        }
        executorService.shutdown();
    }

    @Data
    static class Student {

        public Student(String strNo, String strName) {
            this.strNo = strNo;
            this.strName = strName;
        }

        private String strNo;

        private String strName;
    }
}
