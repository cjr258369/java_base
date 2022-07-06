package ThreadBase.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author chenjunran
 * @date 2022/7/6
 */
public class InterruptDemo2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while(true){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread().getName() + "\t 中断标识："
                            +Thread.currentThread().isInterrupted() + " ， 线程停止");
                    break;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    // InterruptedException 会重置中断标识，所以如果没有下面这句，那么程序将会进入无线循环，因为上面永远无法进入
                    // 因此需要在 InterruptedException 内，再设置一次中断标识
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
                System.out.println("------hello InterruptDemo3");
            }
        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        new Thread(() -> t1.interrupt(), "t2").start();
    }
}
