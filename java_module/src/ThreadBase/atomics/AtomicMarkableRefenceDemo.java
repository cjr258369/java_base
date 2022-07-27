package ThreadBase.atomics;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @author chenjunran
 * @date 2022/7/27
 */
public class AtomicMarkableRefenceDemo {
    //初始值为100，标识位为false，代表没人动过
    static AtomicMarkableReference<Integer> atomicMarkableReference = new AtomicMarkableReference(100, false);

    public static void main(String[] args) {

        new Thread(() -> {
            //获取当前的标识位
            boolean marked = atomicMarkableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + "\t 获取到的默认标识："+marked);

            //暂停1秒钟线程，等待后面的 t2线程 拿到和我一样的标识位
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

            atomicMarkableReference.compareAndSet(100, 1000, marked, !marked);
        }, "t1").start();

        new Thread(() -> {
            //获取当前的标识位
            boolean marked = atomicMarkableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + "\t 获取到的默认标识："+marked);
            //暂停2秒钟线程
            try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
            boolean b = atomicMarkableReference.compareAndSet(100, 2000, marked, !marked);
            System.out.println(Thread.currentThread().getName() + "\t CAS结果："+b);
            System.out.println("当前对象的最新值是：" + atomicMarkableReference.getReference()+ " , 当前对象的最新标识位是：" + atomicMarkableReference.isMarked());
        }, "t2").start();

    }
}
