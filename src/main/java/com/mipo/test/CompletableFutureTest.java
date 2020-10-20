package com.mipo.test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @Classname CompletableFutureTest
 * @Description TODO
 * @Date 2020/8/26 14:54
 * @Created by li.yy
 */
@Slf4j
public class CompletableFutureTest {

    private static final ThreadFactory NAMED_THREAD_FACTORY = new ThreadFactoryBuilder().setNameFormat("COMMODITY-TO-KAFKA-POOL-%d").build();

    /**
     * 自定义线程池
     */
    private static final ThreadPoolExecutor COMMODITY_TO_KAFKA_POOL = new ThreadPoolExecutor(10, 20, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024), NAMED_THREAD_FACTORY, (r, executor) -> {
        if(!executor.isShutdown()) {
            try {
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                log.error("【商品推送ES】 线程池执行异常:", e);
            }
        }
    });

    public void sendTopicMessage(String id) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            //System.out.println(1);X
            System.out.println(Thread.currentThread().getName());
//            try {
//                Thread.sleep(2000l);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            return id;
        }, COMMODITY_TO_KAFKA_POOL);

        try {
            String s = future.get(10, TimeUnit.SECONDS);
            System.out.println(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CompletableFutureTest completableFutureTest = new CompletableFutureTest();
        //completableFutureTest.sendTopicMessage("123");

        IntStream.range(0,50).forEach(t-> {
            completableFutureTest.sendTopicMessage(t+"");
        });
        System.out.println("异步执行完成");
        //COMMODITY_TO_KAFKA_POOL.shutdown();
    }
}
