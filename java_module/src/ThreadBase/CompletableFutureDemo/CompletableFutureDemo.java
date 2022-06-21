package ThreadBase.CompletableFutureDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author chenjunran
 * @date 2022/6/14
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new MyThread());
        //异步使用独立的线程调用
        Thread t1 = new Thread(futureTask, "t1");
        t1.start();
        //获得异步线程的返回值，注意需要抛出异常
        System.out.println(futureTask.get());
    }
}

class MyThread implements Callable<String>{
    @Override
    public String call() throws Exception {
        System.out.println("------- com in call()");
        return "hello Callable";
    }
}
