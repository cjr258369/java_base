package ThreadBase.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author chenjunran
 * @date 2022/7/4
 */
public class InterruptDemo {
    static volatile boolean isStop = false;
    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {
    }

    private static void interruptDemo2() {
        Thread t1 = new Thread(() -> {
            for(int i = 0; i <= 100; i++){
                System.out.println("----" + i);
            }
            System.out.println("t1 的中断标识位02 = "+ Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();
        System.out.println("t1 的中断标识位为 "+ t1.isInterrupted());
        //暂停毫秒
        try {TimeUnit.MILLISECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        //被中断的线程，如果不监听中断状态并做出动作，interrupt 是不能中断线程的。
        t1.interrupt();
        System.out.println("t1 的中断标识位01 = "+ t1.isInterrupted());

        //暂停毫秒
        try {TimeUnit.MILLISECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
        //线程已经打印完 100 ， 线程不处于活动状态，所以中断不会产生影响
        System.out.println("t1 的中断标识位03 = "+ t1.isInterrupted());
    }

    private static void threadInterruptDemo() {
        Thread t1 = new Thread(() -> {
            while(true){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread().getName() + "\t isInterrupted() 被修改为 true ， 线程结束");
                    break;
                }
                System.out.println("t1 ------ hello isInterrupted API");
            }
        }, "t1");
        t1.start();

        System.out.println("t1 的中断标识位为 "+ t1.isInterrupted());

        //暂停毫秒
        try {TimeUnit.MILLISECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        // 通过其他线程来中断，比如 t2 来中断 t1
        //new Thread(() -> t1.interrupt(), "t2").start();
        //t1 自己也可以中断 自己
        t1.interrupt();

        System.out.println("t1 的中断标识位为 "+ t1.isInterrupted());
    }

    private static void atomicBooleanDemo() {
        new Thread(() -> {
            while(true){
                if(atomicBoolean.get()){
                    System.out.println(Thread.currentThread().getName() + "\t atomicBoolean 被修改为 true ， 线程结束");
                    break;
                }
                System.out.println("t1 ------ hello atomicBoolean");
            }
        }, "t1").start();

        //暂停毫秒
        try {TimeUnit.MILLISECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(() -> {
            atomicBoolean.set(true);
            isStop = true;
        }, "t2").start();
    }

    private static void volateDemo() {
        new Thread(() -> {
            while(true){
                if(isStop){
                    System.out.println(Thread.currentThread().getName() + "\t isStop 被修改为 true ， 线程结束");
                    break;
                }
                System.out.println("t1 ------ hello volatile");
            }
        }, "t1").start();

        //暂停毫秒
        try {TimeUnit.MILLISECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(() -> {
            isStop = true;
        }, "t2").start();
    }
}
