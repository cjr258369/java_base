package ThreadBase;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author chenjunran
 * @date 2022/6/15
 */
public class FutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            System.out.println(Thread.currentThread().getName() + "\t --- come in");
            //暂停几秒钟线程
            try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
            return "task over";
        });
        Thread t1 = new Thread(futureTask, "t1");
        t1.start();
        System.out.println(Thread.currentThread().getName() + "\t ----忙其他任务了");
        while(true){
            if(futureTask.isDone()){
                System.out.println(futureTask.get());
                break;
            }else{
                //暂停毫秒
                try {TimeUnit.MILLISECONDS.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("正在处理中...");
            }
        }
    }
}
