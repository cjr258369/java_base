package ThreadBase.Locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 * @author chenjunran
 * @date 2022/6/27
 */
public class ReEntryLockDemo {

    //同步方法
    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+"\t ----come in");
        m2();
        System.out.println(Thread.currentThread().getName()+"\t ----end m1");
    }
    public synchronized void m2(){
        System.out.println(Thread.currentThread().getName()+"\t ----come in");
        m3();
        System.out.println(Thread.currentThread().getName()+"\t ----end m2");
    }
    public synchronized void m3(){
        System.out.println(Thread.currentThread().getName()+"\t ----come in");
    }

    static Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        //同步方法
        //ReEntryLockDemo reEntryLockDemo = new ReEntryLockDemo();
        //new Thread(() -> {
        //    reEntryLockDemo.m1();
        //}, "t1").start();

        new Thread(() -> {
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"\t ----come in 外层调用");
                lock.lock();
                try{
                    System.out.println(Thread.currentThread().getName()+"\t ----come in 中层调用");
                }finally {
                    //故意注释掉，导致实现加锁和解锁的次数不一样
                    //单线程没问题，但如果有多一个t2的时候，就会导致计数器清零出现异常，从而 t2 线程一直获取不到锁
                    //lock.unlock();
                }
            }finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"\t ----come in 外层调用");
            }finally {
                lock.unlock();
            }
        }, "t2").start();


    }

    //同步块 的可重入锁demo
    private static void reEntryM1() {
        final Object object = new Object();
        new Thread(() -> {
            synchronized (object){
                System.out.println(Thread.currentThread().getName()+"\t ----外层调用");
                synchronized (object) {
                    System.out.println(Thread.currentThread().getName() + "\t ----中层调用");
                    synchronized (object){
                        System.out.println(Thread.currentThread().getName()+"\t ----内层调用");
                    }
                }
            }
        }, "t1").start();
    }
}
