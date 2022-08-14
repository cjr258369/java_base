package ThreadBase.rwlock;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁 Demo
 */
public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
        //Demo3 演示 读锁没完成前，写锁不能进来
        MyResource myResource = new MyResource();
        //模拟 10 个线程，写入
        for(int i = 1; i <= 10; i++){
            String key = i + " ";
            new Thread(() -> {
                myResource.writeDemo2(key, key);
            }, key).start();
        }
        //模拟 10 个线程，读取
        for(int i = 1; i <= 10; i++){
            String key = i + " ";
            new Thread(() -> {
                myResource.readDemo3(key);
            }, key).start();
        }

        //暂停几秒钟线程
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

        //模拟 3 个新的写线程，但因为读写互斥，前面的 readDemo3() 方法，用时 2 秒，因此读锁没完成前，写锁不可获得
        for(int i = 1; i <= 3; i++){
            String key = i + " ";
            new Thread(() -> {
                myResource.writeDemo2(key, key);
            }, "新写锁线程 -> " + key).start();
        }
    }

    private static void demo2() {
        //可以看到 Demo2 结果 是 写独占，读共享
        MyResource myResource = new MyResource();
        //模拟 10 个线程，写入
        for(int i = 1; i <= 10; i++){
            String key = i + " ";
            new Thread(() -> {
                myResource.writeDemo2(key, key);
            }, key).start();
        }
        //模拟 10 个线程，读取
        for(int i = 1; i <= 10; i++){
            String key = i + " ";
            new Thread(() -> {
                myResource.readDemo2(key);
            }, key).start();
        }
    }

    private static void demo1() {
        //可以看到 Demo 结果 是一对对的，永远只有一个线程操作资源类，写的时候阻塞，读的时候也阻塞。
        MyResource myResource = new MyResource();
        //模拟 10 个线程，写入
        for(int i = 1; i <= 10; i++){
            String key = i + " ";
            new Thread(() -> {
                myResource.writeDemo1(key, key);
            }, key).start();
        }
        //模拟 10 个线程，读取
        for(int i = 1; i <= 10; i++){
            String key = i + " ";
            new Thread(() -> {
                myResource.readDemo1(key);
            }, key).start();
        }
    }
}

class MyResource{   //资源类，模拟一个简单的缓存
    HashMap<String,String> map = new HashMap<>();
    // 可重入锁 ReentrantLock 等价于 synchronized
    Lock lock = new ReentrantLock();
    // 可重入读写锁，一体两面，读写互斥
    ReadWriteLock rwLock = new ReentrantReadWriteLock();

    //旧的情景：使用非读写锁
    public void writeDemo1(String key, String value){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t"+"正在写入");
            map.put(key,value);
            //模拟业务 ， 暂停 500 毫秒
            try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + "\t"+"写入完成");
        } finally {
            lock.unlock();
        }
    }

    public void readDemo1(String key){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "正在读取");
            String result = map.get(key);
            //模拟业务 ， 暂停 200 毫秒
            try {TimeUnit.MILLISECONDS.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + "\t" + "完成读取\t"+result);
        } finally {
            lock.unlock();
        }
    }

    //新的情景：引入读写锁，提高读读的效率：
    public void writeDemo2(String key, String value){
        //记得区分是写锁还是读锁
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t"+"正在写入");
            map.put(key,value);
            //模拟业务 ， 暂停 500 毫秒
            try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + "\t"+"写入完成");
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void readDemo2(String key){
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "正在读取");
            String result = map.get(key);
            //模拟业务 ， 暂停 200 毫秒
            //try {TimeUnit.MILLISECONDS.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
            //暂停 2000 毫秒，演示 读锁没完成之前，写锁无法获得
            try {TimeUnit.MILLISECONDS.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + "\t" + "完成读取\t"+result);
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void readDemo3(String key){
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "正在读取");
            String result = map.get(key);
            //暂停 2000 毫秒，演示 读锁没完成之前，写锁无法获得
            try {TimeUnit.MILLISECONDS.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + "\t" + "完成读取\t"+result);
        } finally {
            rwLock.readLock().unlock();
        }
    }
}
