package ThreadBase;

import java.util.concurrent.*;

/**
 * @author chenjunran
 * @date 2022/6/1
 */
public class CompletableFutureBuildDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //自定义一个线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            //暂停几秒钟线程
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {throw new RuntimeException(e);}
            return "hello supplyAsync";
        }, threadPool);
        System.out.println(completableFuture.get());

        threadPool.shutdown();

        //CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
        //    System.out.println(Thread.currentThread().getName());
        //    //暂停几秒钟线程
        //    try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {throw new RuntimeException(e);}
        //},threadPool);
        //
        //System.out.println(voidCompletableFuture.get());
    }
}
