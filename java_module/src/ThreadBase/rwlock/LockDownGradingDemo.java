package ThreadBase.rwlock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 锁降级：遵循官网示例：现货区写锁 -> 在获取读锁 -> 在释放写锁 的次序，写锁能够降级为读锁
 *
 * 如果一个线程占有了写锁，在不释放写锁的情况下，它还能占有读锁，即写锁降级为读锁。
 *
 * 读没完成的时候，写锁无法获得锁，必须要等着读锁读完后才有机会写。
 */
public class LockDownGradingDemo {
    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

        //假设 正常情况下：A、B两个线程
        // A 写入
        writeLock.lock();
        System.out.println("----写入");
        writeLock.unlock();

        //B 读取
        readLock.lock();
        System.out.println("----读取");
        readLock.unlock();

        System.out.println("==========");

        // 本例子，如果只有一个线程，就是同一个线程
        writeLock.lock();
        // 业务逻辑 do someting....
        System.out.println("----写入");

        //写锁还未释放，它仍旧可以可以使用读锁，马上读到自己的内容
        readLock.lock();
        // 业务逻辑 do someting....
        System.out.println("----读取");

        writeLock.unlock();
        readLock.unlock();
        // 这个流程就是锁降级，俗称同一个线程的写后读

        System.out.println("==========");

        // 注意锁不能升级，因此不能读后写
        readLock.lock();
        // 业务逻辑 do someting....
        System.out.println("----读取");

        //读锁还未释放，立马开始写
        writeLock.lock();
        // 业务逻辑 do someting....

        writeLock.unlock();
        readLock.unlock();
        // 这是反例：锁不能升级，所以代码会一直卡住。

    }
}
