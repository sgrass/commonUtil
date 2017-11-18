package org.cx.thread.java8;

import sun.awt.SunHints;

import javax.management.StringValueExp;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * @author grass
 * @date 2017/11/16
 *
 * @see java.util.concurrent.CompletionStage
 * @see java.util.concurrent.CompletableFuture
 */
public class CompletableFutureDemo {

    public static void demo1() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        //完成操作可以被其它线程去做
        completableFuture.complete("Hello world");

        String str = completableFuture.get();

        System.out.println(str);
    }

    public static void demo2() throws ExecutionException, InterruptedException {
        //异步执行, 阻塞操作
        CompletableFuture asyncCompletableFuture = CompletableFuture.runAsync(()->{
            System.out.println("hello world");
        });


        //仍然是阻塞操作
        asyncCompletableFuture.get();

        System.out.println("starting...");
    }

    public static void demo3() throws ExecutionException, InterruptedException {
        //supplyAsync方式异步执行，阻塞操作
//        CompletableFuture supplyAsyncCompletableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
//            @Override
//            public String get() {
//                return "hello world";
//            }
//        });
        //lambds
        CompletableFuture<String> supplyAsyncCompletableFuture2 = CompletableFuture.supplyAsync(()-> {
            return "hello world";
        });

        String value = supplyAsyncCompletableFuture2.get();

        System.out.println(value);
        System.out.println("starting...");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        //合并操作，异步
        CompletableFuture combinedCompletableFuture = CompletableFuture.supplyAsync(()-> {

            return String.format("[Thread : %s] hello world...", Thread.currentThread().getName());

        }).thenApply(value ->{

            return value + "来自这里加工下下";

        }).thenApply(value ->{

            return value + LocalDate.now();

        }).thenApply(value ->{
            System.out.println(value);
            return value;

        }).thenRun(()->{
            System.out.println("操作结束");
        }); //类似reactive->flatMap


        System.out.println("starting...");
        while (!combinedCompletableFuture.isDone()) {

        }
    }


}
