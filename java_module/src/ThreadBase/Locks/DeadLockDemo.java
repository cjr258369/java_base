package ThreadBase.Locks;

import java.util.concurrent.TimeUnit;

/**
 * 死锁示例
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        final Object objA = new Object();
        final Object objB = new Object();

        new Thread(() -> {
            synchronized (objA){
                System.out.println(Thread.currentThread().getName() + "\t 自己持有A锁，希望获得 B 锁");
                //暂停几秒钟线程
                try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
                synchronized (objB){
                    System.out.println(Thread.currentThread().getName() + "\t 成功获得 B 锁");
                }
            }
        }, "A").start();

        new Thread(() -> {
            synchronized (objB){
                System.out.println(Thread.currentThread().getName() + "\t 自己持有B锁，希望获得 A 锁");
                //暂停几秒钟线程
                try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
                synchronized (objA){
                    System.out.println(Thread.currentThread().getName() + "\t 成功获得 A 锁");
                }
            }
        }, "B").start();
    }
}
