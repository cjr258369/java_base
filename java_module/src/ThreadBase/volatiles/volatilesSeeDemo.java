package ThreadBase.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * 可见性 demo
 */
public class volatilesSeeDemo {

    //static boolean flag = true;
    static volatile  boolean flag = true;

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ----come in");
            while(flag){
            }
            System.out.println(Thread.currentThread().getName() + "\t ---- flag 被设置为 false，程序停止");
        }, "t1").start();

        //暂停几秒钟线程
        try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}

        //main 线程修改 flag
        flag = false;

        System.out.println(Thread.currentThread().getName() + "\t 修改完成，修改后的flag："+flag);
    }
}
