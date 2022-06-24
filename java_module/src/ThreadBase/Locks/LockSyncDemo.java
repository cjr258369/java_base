package ThreadBase.Locks;

/**
 * @author chenjunran
 * @date 2022/6/23
 */
public class LockSyncDemo {
    /*
    Object obj = new Object();
    //同步代码块
    public void m1(){
        synchronized (obj){
            System.out.println("----hello synchronize code block");
            throw new RuntimeException("----exeception");
        }
    }
    */

    public synchronized void m2(){
        System.out.println("----hello synchronize m2");
    }

    public static synchronized void m3(){
        System.out.println("----hello synchronize m3");
    }

    public static void main(String[] args) {

    }
}


