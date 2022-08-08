package ThreadBase.syncup;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 偏向锁
 * @author chenjunran
 * @date 2022/6/24
 */
public class SaleTicketDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        //模拟 3 个售票员，卖完 50 张票
        new Thread(() -> { for (int i = 0; i < 55; i++) ticket.sale();}, "a").start();
        new Thread(() -> { for (int i = 0; i < 55; i++) ticket.sale();}, "b").start();
        new Thread(() -> { for (int i = 0; i < 55; i++) ticket.sale();}, "c").start();
    }
}

class Ticket{   //资源类
    private int num = 50;

    Object lockObject = new Object();

    public void sale(){
        synchronized (lockObject){
            if(num > 0){
                System.out.println(Thread.currentThread().getName() + "卖出第：\t"+ (num--)+" 张票，还剩下："+num);
            }
        }
    }
}
