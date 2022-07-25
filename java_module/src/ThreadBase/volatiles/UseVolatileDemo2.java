package ThreadBase.volatiles;

import java.util.concurrent.TimeUnit;

public class UseVolatileDemo2 {

    /**
    * 同时 volatile 的使用场景之一：
    * 使用：作为一个布尔状态标志，用于指示发生了一个重要的一次性事件，例如完成初始化或任务结束
    * 理由：状态标志并不依赖于程序内任何其他状态，切通常只有一种状态转换
    * 例子：判断业务是否结束
     */
    private volatile static boolean flag = true;

    public static void main(String[] args) {
        new Thread(() -> {
            while(flag){
                //do something.....
            }
        }, "t1").start();

        //暂停几秒钟线程
        try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(() -> {
            flag = false;
        }, "t2").start();
    }
}
