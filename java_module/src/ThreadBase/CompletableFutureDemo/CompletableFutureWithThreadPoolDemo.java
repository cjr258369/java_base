package ThreadBase.CompletableFutureDemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author chenjunran
 * @date 2022/6/21
 */
public class CompletableFutureWithThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try{
            /*
            CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
                //暂停毫秒
                try {TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("1号任务" + "\t" + Thread.currentThread().getName());
                return "abc";
            }).thenRun(() -> {
                try {TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("2号任务" + "\t" + Thread.currentThread().getName());
            }).thenRun(() -> {
                try {TimeUnit.MILLISECONDS.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("3号任务" + "\t" + Thread.currentThread().getName());
            }).thenRun(() -> {
                try {TimeUnit.MILLISECONDS.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("4号任务" + "\t" + Thread.currentThread().getName());
            });
            */
            CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
                //暂停毫秒
                //try {TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("1号任务" + "\t" + Thread.currentThread().getName());
                return "abc";
            }, threadPool).thenRunAsync(() -> {
                try {TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("2号任务" + "\t" + Thread.currentThread().getName());
            }).thenRun(() -> {
                try {TimeUnit.MILLISECONDS.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("3号任务" + "\t" + Thread.currentThread().getName());
            }).thenRun(() -> {
                try {TimeUnit.MILLISECONDS.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("4号任务" + "\t" + Thread.currentThread().getName());
            });

            System.out.println(completableFuture.get(2L, TimeUnit.SECONDS));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
