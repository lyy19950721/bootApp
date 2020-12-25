package com.mipo.test.test4;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Classname CompletableFutureTest
 * @Description TODO
 * @Date 2020/11/5 16:19
 * @Created by li.yy
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        /*CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.get(2, TimeUnit.SECONDS);
        completableFuture.complete("Future's Result");*/

        // Run a task specified by a Runnable Object asynchronously.
        /*CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            // Simulate a long-running Job
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("I'll run in a separate thread than the main thread.");
        });

        // Block and wait for the future to complete
        future.get();*/

        // Using Lambda Expression
        /*CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of the asynchronous computation";
        });
        // Block and get the result of the Future
        String result = future.get();
        System.out.println(result);*/



        /*Executor executor = Executors.newFixedThreadPool(10);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of the asynchronous computation";
        }, executor);
        System.out.println(future.get());
        ((ExecutorService) executor).shutdown();*/

        // Create a CompletableFuture
       /* CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Rajeev";
        });

        // Attach a callback to the Future using thenApply()
        CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply(name -> {
            return "Hello " + name;
        });

        // Block and get the result of the future.
        System.out.println(greetingFuture.get()); // Hello Rajeev
        System.out.println("lalalla");*/

       /* CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Rajeev";
        }).thenApply(name ->
             "Hello " + name
        ).thenApply(greeting ->
             greeting + ", Welcome to the CalliCoder Blog"
        );

        System.out.println(welcomeText.get());
        // Prints - Hello Rajeev, Welcome to the CalliCoder Blog*/

        System.out.println("Retrieving weight.");
        CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 65.0;
        });

        System.out.println("Retrieving height.");
        CompletableFuture<Double> heightInCmFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 177.8;
        });

        System.out.println("Calculating BMI.");
        CompletableFuture<Double> combinedFuture = weightInKgFuture
                .thenCombine(heightInCmFuture, (weightInKg, heightInCm) -> {
                    System.out.println(weightInKg+"============"+heightInCm);
                    Double heightInMeter = heightInCm/100;
                    return weightInKg/(heightInMeter*heightInMeter);
                });

        System.out.println("Your BMI is - " + combinedFuture.get());



    }
}
