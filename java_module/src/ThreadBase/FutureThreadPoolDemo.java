package ThreadBase;

import java.util.concurrent.*;

/**
 * @author chenjunran
 * @date 2022/6/14
 */
public class FutureThreadPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //需求：3个任务，目前开启多个异步任务线程来处理，耗时：
        //使用线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        long startTime = System.currentTimeMillis();
        //任务1
        FutureTask<String> futureTask1 = new FutureTask<String>(() -> {
            try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
            return "task1 over";
        });
        threadPool.submit(futureTask1);
        //任务2
        FutureTask<String> futureTask2 = new FutureTask<String>(() -> {
            try {TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
            return "task2 over";
        });
        threadPool.submit(futureTask2);
        //打印任务结果
        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());
        //任务3：main 线程 自己认领第三个任务：
        try {TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
        long endTime = System.currentTimeMillis();
        System.out.println("---costTime：" + (endTime - startTime) + " ms");
        System.out.println(Thread.currentThread().getName() + "\t  ----end");
        //关闭线程池
        threadPool.shutdown();
    }

    public static void m1(String[] args) {
        //需求：3个任务，目前只有1个线程 main 来处理，耗时：
        long startTime = System.currentTimeMillis();
        //暂停几秒钟线程
        try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
        try {TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
        try {TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
        long endTime = System.currentTimeMillis();
        System.out.println("---costTime：" + (endTime - startTime) + " ms");
        System.out.println(Thread.currentThread().getName() + "\t  ----end");
    }
}
