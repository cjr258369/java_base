package ThreadBase.atomics;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * 50个线程，每个线程100w 次点赞，取出总点赞数
 */
public class AccumulatorCompareDemo {
    public static final int _1W = 10000;
    public static final int THREAD_NUMBER = 50;

    public static void main(String[] args) throws InterruptedException {
        ClickNumber clickNumber = new ClickNumber();

        long startTime;
        long endTime;

        CountDownLatch countDownLatch1 = new CountDownLatch(THREAD_NUMBER);
        CountDownLatch countDownLatch2 = new CountDownLatch(THREAD_NUMBER);
        CountDownLatch countDownLatch3 = new CountDownLatch(THREAD_NUMBER);
        CountDownLatch countDownLatch4 = new CountDownLatch(THREAD_NUMBER);

        startTime = System.currentTimeMillis();
        for(int i = 1; i <= THREAD_NUMBER; i++){
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100 * _1W; j++) {
                        clickNumber.clickBySynchronized();
                    }
                } finally {
                    countDownLatch1.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch1.await();
        endTime = System.currentTimeMillis();
        System.out.println("clickBySynchronized    costTime：" + (endTime - startTime) + " ms , result = " + clickNumber.number);

        startTime = System.currentTimeMillis();
        for(int i = 1; i <= THREAD_NUMBER; i++){
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100 * _1W; j++) {
                        clickNumber.clickByAtomicLong();
                    }
                } finally {
                    countDownLatch2.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch2.await();
        endTime = System.currentTimeMillis();
        System.out.println("clickByAtomicLong      costTime：" + (endTime - startTime) + " ms , result = " + clickNumber.atomicLong.get());


        startTime = System.currentTimeMillis();
        for(int i = 1; i <= THREAD_NUMBER; i++){
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100 * _1W; j++) {
                        clickNumber.clickByLongAdder();
                    }
                } finally {
                    countDownLatch3.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch3.await();
        endTime = System.currentTimeMillis();
        System.out.println("clickByLongAdder       costTime：" + (endTime - startTime) + "  ms , result = " + clickNumber.longAdder.sum());


        startTime = System.currentTimeMillis();
        for(int i = 1; i <= THREAD_NUMBER; i++){
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100 * _1W; j++) {
                        clickNumber.clickByLongAccumulator();
                    }
                } finally {
                    countDownLatch4.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch4.await();
        endTime = System.currentTimeMillis();
        System.out.println("clickByLongAccumulator costTime：" + (endTime - startTime) + "  ms , result = " + clickNumber.longAccumulator.get());
    }
}

/**
 * 各种统计方法比较
 */
class ClickNumber{
    int number = 0;
    public synchronized void clickBySynchronized(){
        number++;
    }

    AtomicLong atomicLong = new AtomicLong(0);
    public void clickByAtomicLong(){
        atomicLong.getAndIncrement();
    }

    LongAdder longAdder = new LongAdder();
    public void clickByLongAdder(){
        longAdder.increment();
    }

    LongAccumulator longAccumulator = new LongAccumulator((x, y) -> x + y, 0);
    public void clickByLongAccumulator(){
        longAccumulator.accumulate(1);
    }
}
