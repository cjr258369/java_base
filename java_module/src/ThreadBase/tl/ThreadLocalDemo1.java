package ThreadBase.tl;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 需求1：5个销售卖房子，集团高层只关心销售总量的准确统计数
 *
 * 需求2：5个销售卖完随机数的房子，各自能够有各自自己的销售单数，不和所有的混在一起
 */
public class ThreadLocalDemo1 {
    public static void main(String[] args) {
        xuqiu2();
    }

    private static void xuqiu2() {
        House house = new House();
        for(int i = 1; i <= 5; i++){
            new Thread(() -> {
                //造个随机数，意思是当前销售（线程），卖出的房子数量
                int count = new Random().nextInt(5) + 1;
                for (int j = 1; j <= count; j++) {
                    house.saleValueByThreadLocal();
                }
                System.out.println(Thread.currentThread().getName() + " 号销售，卖出了：" + house.saleValue.get() +" 套");
            }, String.valueOf(i)).start();
        }

        //暂停几秒钟线程
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

        //System.out.println(Thread.currentThread().getName() + "\t 共计卖出多少套：" + house.saleCount);
    }

    //需求1的代码 Demo
    private static void xuqiu1() {
        House house = new House();
        for(int i = 1; i <= 5; i++){
            new Thread(() -> {
                //造个随机数，意思是当前销售（线程），卖出的房子数量
                int count = new Random().nextInt(5) + 1;
                System.out.println("count = " + count);
                for (int j = 1; j <= count; j++) {
                    house.saleHouse();
                }

            }, String.valueOf(i)).start();
        }

        //暂停几秒钟线程
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

        System.out.println(Thread.currentThread().getName() + "\t 共计卖出多少套：" + house.saleCount);
    }
}

//资源类
class House{
    int saleCount = 0;

    public synchronized void saleHouse(){
        saleCount++;
    }

    /*ThreadLocal<Integer> saleValue = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };*/

    ThreadLocal<Integer> saleValue = ThreadLocal.withInitial(() -> 0);

    public void saleValueByThreadLocal(){
        //先取出线程自己原先的值，然后 加1
        saleValue.set(saleValue.get() + 1);
    }

}
