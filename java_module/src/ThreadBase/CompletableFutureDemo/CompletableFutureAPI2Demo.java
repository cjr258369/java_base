package ThreadBase.CompletableFutureDemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author chenjunran
 * @date 2022/6/21
 */
public class CompletableFutureAPI2Demo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(() ->{
            //暂停几秒钟线程
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(1);
            return 1;
        }, threadPool).handle((f, e) -> {
            int i = 10/0;     //测试异常
            System.out.println(2);
            return f + 2;
        }).handle((f, e) -> {
            System.out.println(3);
            return f + 2;
        }).whenComplete((v, e) -> {
            if(e == null){
                System.out.println("-------计算结果="+v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });
        threadPool.shutdown();
        System.out.println(Thread.currentThread().getName() + "\t 主线程先去忙其他任务");
    }

    public static void thenApplyMethod(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(() ->{
            //暂停几秒钟线程
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(1);
            return 1;
        }, threadPool).thenApply(f -> {
            //int i = 10/0;     //测试异常
            System.out.println(2);
            return f + 2;
        }).thenApply(f -> {
            System.out.println(3);
            return f + 2;
        }).whenComplete((v, e) -> {
            if(e == null){
                System.out.println("-------计算结果="+v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });
        threadPool.shutdown();
        System.out.println(Thread.currentThread().getName() + "\t 主线程先去忙其他任务");
    }
}
