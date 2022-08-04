package ThreadBase.tl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池 使用 ThreadLocal 的 Demo
 * @author chenjunran
 * @date 2022/8/4
 */
public class ThreadLocalDemo2 {
    public static void main(String[] args) {
        MyData myData = new MyData();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.submit(() ->{
                    try {
                        Integer beforeInt = myData.threadLocal.get();
                        myData.add();
                        Integer afterInt = myData.threadLocal.get();
                        System.out.println(Thread.currentThread().getName() + "\t beforeInt="+beforeInt+ "\t afterInt="+afterInt);
                    } finally {
                        //每次清空后，即便是新进来的请求，复用线程池里的线程，也不会有旧数据的残留
                        myData.threadLocal.remove();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    //错误的示范：
    private static void demo1() {
        //没有对 ThreadLocal 进行回收的时候，10个线程并发增加自己的数据
        //打印出来的效果是：因为线程池复用线程的原因，每个线程都沿用之前的数据，而不是每次都使用从0开始加1
        //问题点在于：因为ThreadLocal 没有执行 remove，而导致每个复用的线程执行新任务的时候，依然使用的是旧任务遗留来来的值
        MyData myData = new MyData();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.submit(() ->{
                    Integer beforeInt = myData.threadLocal.get();
                    myData.add();
                    Integer afterInt = myData.threadLocal.get();

                    System.out.println(Thread.currentThread().getName() + "\t beforeInt="+beforeInt+ "\t afterInt="+afterInt);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}

class MyData{
    ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public void add(){
        threadLocal.set(threadLocal.get() + 1);
    }
}
