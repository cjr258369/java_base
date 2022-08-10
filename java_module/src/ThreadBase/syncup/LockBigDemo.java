package ThreadBase.syncup;

/**
 * 无聊的 JIT 编译器 对锁的优化：锁消除 与 锁粗化
 * 锁粗化 Demo
 *
 * 假如方法中首尾相接， 前后相邻的都是同一个锁对象，那么 JIT 编译器就会把这几个 synchronized 块合并成一个大块，
 * 加粗加大范围，一次申请使用即可，避免此次的盛情和释放锁，提升了性能
 */
public class LockBigDemo {
    static Object objectLock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (objectLock){
                System.out.println("1111");
            }
            synchronized (objectLock){
                System.out.println("2222");
            }
            synchronized (objectLock){
                System.out.println("3333");
            }
            synchronized (objectLock){
                System.out.println("4444");
            }
            //  上面写语法当然没问题，但 加锁解锁了4次，
            //  所以 JIT 底层实际上做了优化，就是只加锁解锁一次，就等同于下方的代码
            synchronized (objectLock){
                System.out.println("1111");
                System.out.println("2222");
                System.out.println("3333");
                System.out.println("4444");
            }
        }, "t1").start();
    }

}
