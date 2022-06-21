package ThreadBase.CompletableFutureDemo;

import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author chenjunran
 * @date 2022/6/17
 */
public class CompletableFutureUseDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try{
            CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "----- come in");
                int result = ThreadLocalRandom.current().nextInt(10);
                //暂停几秒钟线程
                try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("--- 1 秒钟后出结果：" + result);
                if(result > 5) result = 10 / 0;
                return result;
            }, threadPool).whenComplete((v, e) -> {
                if(e == null){
                    System.out.println("没有异常，计算完成，更新系统，updateVal="+v);
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                System.out.println("异常情况："+e.getCause()+"\t "+e.getMessage());
                return null;
            });
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
        System.out.println(Thread.currentThread().getName() + " 主线程去忙其他任务");
    }

    public static void future1(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Object> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "----- come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            //暂停几秒钟线程
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println("--- 1 秒钟后出结果：" + result);
            return result;
        });

        System.out.println(Thread.currentThread().getName() + " 主线程去忙其他任务");
        System.out.println(completableFuture.get());
    }
}
