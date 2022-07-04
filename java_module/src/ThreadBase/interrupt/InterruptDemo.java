package ThreadBase.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author chenjunran
 * @date 2022/7/4
 */
public class InterruptDemo {
    static volatile boolean isStop = false;
    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {

    }

    private static void atomicBooleanDemo() {
        new Thread(() -> {
            while(true){
                if(atomicBoolean.get()){
                    System.out.println(Thread.currentThread().getName() + "\t atomicBoolean 被修改为 true ， 线程结束");
                    break;
                }
                System.out.println("t1 ------ hello volatile");
            }
        }, "t1").start();

        //暂停毫秒
        try {TimeUnit.MILLISECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(() -> {
            atomicBoolean.set(true);
            isStop = true;
        }, "t2").start();
    }

    private static void volateDemo() {
        new Thread(() -> {
            while(true){
                if(isStop){
                    System.out.println(Thread.currentThread().getName() + "\t isStop 被修改为 true ， 线程结束");
                    break;
                }
                System.out.println("t1 ------ hello volatile");
            }
        }, "t1").start();

        //暂停毫秒
        try {TimeUnit.MILLISECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(() -> {
            isStop = true;
        }, "t2").start();
    }
}
