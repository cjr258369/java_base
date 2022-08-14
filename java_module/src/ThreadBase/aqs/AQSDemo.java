package ThreadBase.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AQSDemo {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();  //非公平锁

        //  A B C 三个顾客，去银行办理业务，A先到，此时窗口空无一人，他有限获得办理业务的机会，去办理业务
        //  A耗时严重，估计长期占有窗口
        new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("------ A come in");
                //暂停几分钟线程
                try {TimeUnit.MINUTES.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
            } finally {
                reentrantLock.unlock();
            }
        }, "A").start();

        // B 是第二个顾客，B 一看受理窗口被 A 占用，只能去候客区等待，进入 AQS 队列，等待着 A 办理完成，尝试取抢占受理窗口
        new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("------ B come in");
            } finally {
                reentrantLock.unlock();
            }
        }, "B").start();

        //  C 是第三个顾客，C一看到受理窗口被A占用，只能去候客区等待，进入 AQS 队列，等待着 A 办理完成，尝试取抢占受理窗口，前面是 B 顾客，FIFO
        new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("------ B come in");
            } finally {
                reentrantLock.unlock();
            }
        }, "C").start();

        //后续 DEFG ...... 以此类推
        new Thread(() -> {
            reentrantLock.lock();
            try {
                //..........
            } finally {
                reentrantLock.unlock();
            }
        }, "D").start();
    }
}
