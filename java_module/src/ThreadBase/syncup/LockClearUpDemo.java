package ThreadBase.syncup;

/**
 * 无聊的 JIT 编译器 对锁的优化：锁消除 与 锁粗化
 * 锁消除 Demo
 *
 * 从 JIT 的角度看相当于无视它：synchronized（o） 不存在了
 * 这个锁对象并没有被共用扩散到其他线程使用
 * 极端的说就是根本没有加这个锁对象的底层机器码，消除了锁的作用。
 */
public class LockClearUpDemo {
    static Object objectLock = new Object();
    public void m1(){
        //多线程同时操作一个对象，正常写法
        //synchronized (objectLock){
        //    System.out.println("----hello SynchronizedUpDemo3");
        //}
        //这么写语法不会报错，但会产生锁消除问题，JIT 编译器会无视它
        //原意是希望，只有锁住 objectLock，只有一个线程能够进来 m1 方法，但方法内又 new 了 新的 Object，那么就不是同一把锁了，每个线程自己new了个新的对象
        //因此这个 Object 是锁不住的。
        //这是非正常的，编译器会无视这个 synchronized，因为原意是要锁住同一个对象，但这里每个线程自己new一个了，这就等于毫无意义了。
        Object o = new Object();
        synchronized (o){
            System.out.println("----hello SynchronizedUpDemo3 \t"+o.hashCode()+"\t"+objectLock.hashCode());
        }
    }

    public static void main(String[] args) {
        LockClearUpDemo lockClearUpDemo = new LockClearUpDemo();

        for(int i = 1; i <= 10; i++){
            new Thread(() -> {
                lockClearUpDemo.m1();
            }, String.valueOf(i)).start();
        }
    }
}
