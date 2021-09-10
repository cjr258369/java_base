package Multithreading.RunnableDemo;

public class TestThread {
    public static void main(String args[]){
        //测试 Runnable 方式的多线程
        RunnableDemo r1 = new RunnableDemo("thread1");
        r1.start();
        RunnableDemo r2 = new RunnableDemo("thread2");
        r2.start();
        RunnableDemo r3 = new RunnableDemo("thread3");
        r3.start();
    }
}
