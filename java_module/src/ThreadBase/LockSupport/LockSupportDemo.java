package ThreadBase.LockSupport;

import java.util.concurrent.TimeUnit;

/**
 * @author chenjunran
 * @date 2022/7/20
 */
public class LockSupportDemo {
    public static void main(String[] args) {
        nomal();
    }

    private static void exception2() {
        Object objectLock = new Object();
        new Thread(() -> {
            try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
            synchronized (objectLock){
            System.out.println(Thread.currentThread().getName() + " \t ----come in");
            try {
                objectLock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " \t ----被唤醒");
            }
        }, "t1").start();

        //暂停几秒钟线程
        //try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(() -> {
            synchronized (objectLock){
            objectLock.notify();
            System.out.println(Thread.currentThread().getName() + " \t ----发出通知");
            }
        }, "t2").start();
    }

    private static void excepetion1() {
        Object objectLock = new Object();
        new Thread(() -> {
            //synchronized (objectLock){
                System.out.println(Thread.currentThread().getName() + " \t ----come in");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " \t ----被唤醒");
            //}
        }, "t1").start();

        //暂停几秒钟线程
        try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(() -> {
            //synchronized (objectLock){
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + " \t ----发出通知");
            //}
        }, "t2").start();
    }

    //正常情况
    private static void nomal() {
        Object objectLock = new Object();
        new Thread(() -> {
            synchronized (objectLock){
                System.out.println(Thread.currentThread().getName() + " \t ----come in");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " \t ----被唤醒");
            }
        }, "t1").start();

        //暂停几秒钟线程
        try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(() -> {
            synchronized (objectLock){
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + " \t ----发出通知");
            }
        }, "t2").start();
    }
}
