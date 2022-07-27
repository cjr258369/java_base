package ThreadBase.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 手动实现 自旋锁，重温 CAS 思想，不能使用 synchronized 和 其他 JUC 锁，需要自己实现
 * 自旋锁好处：循环比较获取获取，不会进入阻塞，如 wait；
 *
 * 通过 CAS 操作完成自旋锁，A线程 进来先调用 mylock 方法，自己持有锁 5 秒，B线程随后进来
 * 发现当前锁已被其他线程持有，所以只能通过自旋等待，直到 A 释放锁后 B 抢到。
 */
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " \t ---- come in");
        //上锁的时候，就是判断 原坑位 有没有人，没人就占坑
        while (!atomicReference.compareAndSet(null, thread)) {
            //切记，如果占坑成功，则是 True，那么取反 false，就可以跳出循环
        }
    }

    public void unlock(){
        Thread thread = Thread.currentThread();
        //解锁的时候，就是判断 原坑位 是不是自己，是的话置为null，意思是我走了，没人了
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + " \t ---- 任务结束，坑位留空");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() -> {
            spinLockDemo.lock();
            //暂停几秒钟线程
            try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
            spinLockDemo.unlock();
        }, "A").start();

        //暂停毫秒，以保证线程A，先于 线程B 启动
        try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(() -> {
            spinLockDemo.lock();
            //doSomeThing...
            spinLockDemo.unlock();
        }, "B").start();
    }
}
