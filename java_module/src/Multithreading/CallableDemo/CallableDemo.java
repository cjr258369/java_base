package Multithreading.CallableDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 通过 Callable 创建线程
 * 1. 创建 Callable 接口的实现类，并实现 call() 方法，该方法将作为线程的执行体，并且有返回值
 * 2. 创建 Callable 实现类的实例，使用 FutureTask 类来包装 Callable 对象，改 FutureTask 对象封装了该 Callable 对象的 call() 方法的返回值
 * 3. 使用 FutureTask 对象作为 Thread 对象的 target 创建，并启动新线程
 * 4. 调用 FutureTask 的 get() 方法来获得子线程结束后的值
 */
public class CallableDemo implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int i=0;
        for(;i<100;i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        return i;
    }

    public static void main(String args[]){
        CallableDemo call = new CallableDemo();
        FutureTask<Integer> futureTask = new FutureTask<>(call);

        for(int i=0; i<100;i++){
            System.out.println(Thread.currentThread().getName() + " 的循环变量的 i 的值："+i);
            if(i == 20){
                new Thread(futureTask,"有返回值的线程启动").start();
            }
        }

        try {
            System.out.println("子线程的返回值："+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
