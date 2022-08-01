package ThreadBase.atomics;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 以一种线程安全的方式操作非线程安全对象的某些字段
 *
 * 10 个线程，每个线程增加余额 1000 次
 * 不使用 synchronized ，尝试使用 AtomicIntegerFieldUpdater 来实现
 *
 * @author chenjunran
 * @date 2022/8/1
 */
public class AtomicIntegerFieldUpdaterDemo {
    public static void main(String[] args) throws InterruptedException {
        BankAccount user1 = new BankAccount();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for(int i = 1; i <= 10; i++){
            new Thread(() -> {
                try {
                    for(int j = 1; j <= 1000; j++){
                        user1.addMoney(user1);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + "\t result=" + user1.money);
    }

    //没使用原子类以前
    private static void before() throws InterruptedException {
        BankAccount user1 = new BankAccount();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for(int i = 1; i <= 10; i++){
            new Thread(() -> {
                try {
                    for(int j = 1; j <= 1000; j++){
                        user1.add();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + "\t result=" + user1.money1);
    }
}

class BankAccount{
    String bankname = "ICBC";

    //以前

    //余额
    int money1 = 0;
    public synchronized void add(){
        money1++;
    }

    //现在
    //余额。第一步：更新的对象属性必须使用 public volatile 修饰。
    public volatile int money = 0;
    //第二步：因为对象的属性方法修改类型原子类都是抽象类，所以每次使用都必须使用静态方法 newUpdater() 创建一个更新器，并且需要设置想要更新的类和属性。
    AtomicIntegerFieldUpdater<BankAccount> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(BankAccount.class, "money");
    //不加 synchronized，同时保证 高性能、原子性。、
    public void addMoney(BankAccount bankAccount){
        fieldUpdater.getAndIncrement(bankAccount);
    }

}
