package ThreadBase.syncup;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @date 2022/8/10
 */
public class SynchronizedUpDemo2 {

    public static void main(String[] args) {

    }

    //偏向锁过程中，遇到一致性哈希计算请求，立马撤销偏向模式，膨胀为重量级锁。
    private static void demo2() {
        //先睡眠 5 秒，保证开启偏向锁
        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
        Object o = new Object();

        synchronized (o){
            //没有重写，一致性哈希，重写无效
            o.hashCode();
            System.out.println("偏向锁过程中，遇到一致性哈希计算请求，立马撤销偏向模式，膨胀为重量级锁。");
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

    //demo1 : 当一个对象已经计算过 identity hash code，他就无法进入偏向锁状态，而会跳过偏向锁，直接升级轻量级锁。
    private static void demo1() {
        //先睡眠 5 秒，保证开启偏向锁
        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
        Object o = new Object();
        System.out.println("本应是偏向锁");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        //没有重写，一致性哈希，重写无效
        //当一个对象已经计算过 identity hash code ，他就无法再进入偏向锁状态
        o.hashCode();

        synchronized (o){
            System.out.println("本应是偏向锁，但是由于计算过一致性哈希，会直接升级为轻量级锁");
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

}
