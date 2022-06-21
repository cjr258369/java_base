package ThreadBase.CompletableFutureDemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author chenjunran
 * @date 2022/6/21
 */
public class CompletableFutureCombineDemo {
    public static void main(String[] args) {
        CompletableFuture<Integer> result = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in 1");
            //暂停几秒钟线程
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            return 10;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in 2");
            //暂停几秒钟线程
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            return 20;
        }), (x1, x2) -> {
            System.out.println("开始对异步任务的结果进行合并");
            return x1 + x2;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in 3");
            //暂停几秒钟线程
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            return 20;
        }), (x1, x2) -> {
            System.out.println("开始对异步任务的结果进行合并");
            return x1 + x2;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in 4");
            //暂停几秒钟线程
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            return 20;
        }), (x1, x2) -> {
            System.out.println("开始对异步任务的结果进行合并");
            return x1 + x2;
        });

        System.out.println("主线程结束");
        System.out.println(result.join());
    }

    public static void test1(String[] args) {
        CompletableFuture<Integer> integerCompletableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 启动");
            //暂停几秒钟线程
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            return 10;
        });

        CompletableFuture<Integer> integerCompletableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 启动");
            //暂停几秒钟线程
            try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
            return 20;
        });

        CompletableFuture<Integer> result = integerCompletableFuture1.thenCombine(integerCompletableFuture2, (x1, x2) -> {
            System.out.println("开始对异步任务的结果进行合并");
            return x1 + x2;
        });

        System.out.println(result.join());
    }
}
