package ThreadBase.atomics;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenjunran
 * @date 2022/7/27
 */
public class AtomicIntegerDemo {
    //50 个线程来操作
    public static final int SIZE = 50;

    public static void main(String[] args) throws InterruptedException {
        //在没有使用 countDownLatch 的时候，但每次等多少秒，这个处理方式 并不和谐，因为等的时间少了，没算完，等的时间多了，又浪费
        //而且正常的业务代码，也不可能等，也算不出来时间。所以最好的方式是增加 countDownLatch，让上面线程算完了，自己来告诉 main 线程
        //因此 使用 CountDownLatch 来改造

        MyNumber myNumber = new MyNumber();
        //初始为 50 个线程
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);

        for(int i = 1; i <= SIZE; i++){
            new Thread(() -> {
                try{
                    //每个线程累加 1000 次
                    for(int j = 1; j <= 1000; j++){
                        myNumber.addPlusPlus();
                    }
                }finally {
                    //每一个线程完成，则减少锁存器的计数，如果计数达到零，释放所有等待的线程
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }
        //当前线程等到锁存器计数到零 , 释放等待的线程
        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + "\t result = " + myNumber.atomicInteger.get());
    }

    private static void noCountDownLatch() {
        MyNumber myNumber = new MyNumber();

        for(int i = 1; i <= SIZE; i++){
            new Thread(() -> {
                //每个线程累加 1000 次
                for(int j = 1; j <= 1000; j++){
                    myNumber.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }

        //如果不等待上面线程计算结果，就get, 那么每次的值都大概率会不一样
        //普通情况下的等待：sleep，
        try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}

        System.out.println(Thread.currentThread().getName() + "\t result = " + myNumber.atomicInteger.get());
    }
}

class MyNumber{
    AtomicInteger atomicInteger = new AtomicInteger();

    public void addPlusPlus(){
        atomicInteger.getAndIncrement();
    }
}
