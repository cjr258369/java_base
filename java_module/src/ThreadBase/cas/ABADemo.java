package ThreadBase.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 多线程环境下，使用 AtomicStampedReference 解决 ABA 问题
 * @author chenjunran
 * @date 2022/7/27
 */
public class ABADemo {
    static AtomicInteger atomicInteger = new AtomicInteger(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 首次版本号："+stamp);
            //暂停500毫秒，以保证后面 t4线程 初始化拿到的版本号和我一样
            try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}

            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t 2次版本号："+atomicStampedReference.getStamp());

            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t 3次版本号："+atomicStampedReference.getStamp());
        }, "t3").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 首次版本号："+stamp);
            //暂停 1 秒，等待上面的 t3线程，发生 ABA
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

            boolean b = atomicStampedReference.compareAndSet(101, 100, stamp, stamp + 1);
            //意思是：就算值内容仍符合预期，但版本号不符合预期，因此 CAS 依旧失败
            System.out.println(Thread.currentThread().getName() + " 的 CAS 结果：" + b +" ，当前的值是：" + atomicStampedReference.getReference()+ " , 当前的流水是：" + atomicStampedReference.getStamp());
        }, "t4").start();

    }

    /**
     * ABA 发生了。
     * 单纯的判断值，并不安全。
     */
    private static void abaHappen() {
        //用普通的原子类，复现 ABA 问题
        new Thread(() -> {
            //先改成 101，后面逻辑运算后又改回100，
            atomicInteger.compareAndSet(100, 101);
            //暂停毫秒
            try {TimeUnit.MILLISECONDS.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
            atomicInteger.compareAndSet(101, 100);
        }, "t1").start();

        new Thread(() -> {
            //线程t2 根本不知道 100 被改为过101，因为内存的值还是100
            //暂停毫秒
            try {TimeUnit.MILLISECONDS.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println("CAS 判断结果：" + atomicInteger.compareAndSet(100, 2022) + " \t 看看有没有修改成功："+atomicInteger.get());
        }, "t2").start();
    }
}
