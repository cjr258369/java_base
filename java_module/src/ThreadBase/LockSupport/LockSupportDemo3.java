package ThreadBase.LockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author chenjunran
 * @date 2022/7/20
 */
public class LockSupportDemo3 {
    public static void main(String[] args) {
        nomal();
    }

    private static void exception2() {
        Thread t1 = new Thread(() -> {
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + "\t ----come in");
            LockSupport.park();
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t ----被唤醒");
        }, "t1");
        t1.start();

        //暂停几秒钟线程
        //try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(() -> {
            LockSupport.unpark(t1);
            LockSupport.unpark(t1);
            LockSupport.unpark(t1);
            LockSupport.unpark(t1);
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
        }, "t2").start();
    }

    private static void exception1() {
        Thread t1 = new Thread(() -> {
            try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + "\t ----come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t ----被唤醒");
        }, "t1");
        t1.start();

        //暂停几秒钟线程
        //try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(() -> {
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
        }, "t2").start();
    }

    private static void nomal() {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ----come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t ----被唤醒");
        }, "t1");
        t1.start();

        //暂停几秒钟线程
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(() -> {
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
        }, "t2").start();
    }

}
