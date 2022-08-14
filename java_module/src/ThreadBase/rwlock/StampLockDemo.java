package ThreadBase.rwlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * StampLock = ReentrantReadWriteLock + 读的过程中也允许获取写锁介入
 *
 * 三种模式，均写 Demo
 */
public class StampLockDemo {

    static int number = 20;
    static StampedLock stampedLock = new StampedLock();

    public void write(){
        long stamp = stampedLock.writeLock();

        System.out.println(Thread.currentThread().getName() + "\t" + "写线程准备修改");
        try {
            number++;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
        System.out.println(Thread.currentThread().getName() + "\t" + "写线程修改完毕，result=" + number);
    }

    //传统的悲观读锁【读的过程中，写锁无法介入】
    public void readDemo1(){
        long stamp = stampedLock.readLock();
        System.out.println(Thread.currentThread().getName() + "\t" + " 读锁 come in，需要读取 4 秒钟");

        for (int i = 0; i < 4; i++) {
            //暂停几秒，执行业务逻辑
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + "\t" + " 读取中...");
        }

        try {
            int result = number;
            System.out.println(Thread.currentThread().getName() + "\t"+" , 读取到结果为：" + result);
            System.out.println("写线程没有修改成功，读锁时候写锁无法介入，传统的读写互斥");
        } finally {
            stampedLock.unlockRead(stamp);
        }
    }

    //新版本增强的功能：乐观读【读的过程中，写锁可以介入】
    public void readDemo2(){
        long stamp = stampedLock.tryOptimisticRead();
        int result = number;
        // 故意间隔 4秒，很乐观的认为，读取过程中，没有其他线程修改 number 的值，具体靠 API（stampedLock.validate()）方法的判断
        System.out.println("读锁已进入，需要读4秒，4秒前，stampedLock.validate(传入需要校验的戳记)方法的值为(true 代表无修改，false 代表有修改) = " + "\t" + stampedLock.validate(stamp));

        for (int i = 1; i <= 4; i++) {
            //暂停几秒，执行业务逻辑
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + "\t" +"正在读取中.....第 "+i+
                    " 秒后，stampedLock.validate(传入需要校验的戳记)方法值为(true 代表无修改，false 代表有修改)方法的值为" + "\t" + stampedLock.validate(stamp));
        }

        if(stampedLock.validate(stamp)){
            System.out.println(Thread.currentThread().getName() + "\t"+"读取操作成功，finally value = "+ result);
        }else{
            System.out.println("有其他线程修改过，有写操作，需要重新操作");
            //升级为 悲观读锁
            stamp = stampedLock.readLock();

            try {
                System.out.println("从乐观读锁，升级为 悲观读锁");
                result = number;
                System.out.println("重新悲观读后，finally value = " + result);
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
    }


    public static void main(String[] args) {

        StampLockDemo stampLockDemo = new StampLockDemo();
        new Thread(() -> {
            stampLockDemo.readDemo2();
        }, "readThread").start();

        //暂停2秒钟线程后，写线程介入【演示读过程中，读未释放锁时，写锁可以介入，读锁触发判断，执行悲观读】
        //try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
        //暂停6秒钟线程后，写线程介入【演示读过程中，读锁已释放，写锁介入，读锁不触发判断，执行的是乐观读】
        try {TimeUnit.SECONDS.sleep(6);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "---- come in");
            stampLockDemo.write();
        }, "writeThread").start();
    }

    //传统的悲观读锁
    private static void demo1() {
        StampLockDemo stampLockDemo = new StampLockDemo();
        new Thread(() -> {
            stampLockDemo.readDemo1();
        }, "readThread").start();

        //读会读4秒，所以暂停1秒钟线程后，写线程介入
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "---- come in");
            stampLockDemo.write();
        }, "writeThread").start();
    }
}
