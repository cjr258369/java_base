package ThreadBase.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * Volatile 无原子性 demo
 */
public class VolatileNoAtomicDemo {

    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();
        MyNumber2 myNumber2 = new MyNumber2();

        //模拟 10 个线程，每个线程操作 number++ 1000 次
        for(int i = 0; i < 10; i++){
            new Thread(() -> {
                for(int j = 0; j < 1000; j++){
                    myNumber.addPlusPlus();
                    myNumber2.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }

        //暂停几秒钟线程
        try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}

        System.out.println(myNumber.number);
        System.out.println(myNumber2.number);
    }
}

// 同步块能保证结果正确
class MyNumber{
    int number;

    public synchronized void addPlusPlus(){
        number++;
    }
}

// 单纯的 Volatile 并不能能保证结果正确，因为 Volatile 不保证原子性
class MyNumber2{
    volatile int number;

    public void addPlusPlus(){
        number++;
    }
}
