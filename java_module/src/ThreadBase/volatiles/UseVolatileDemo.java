package ThreadBase.volatiles;

public class UseVolatileDemo {

    /**
     * 使用：当读远多于写，结合使用内部锁和 volatile 变量来减少同步的开销
     * 理由：利用 volatile 保证读取操作的可见性，利用 synchronized 保证复合操作的原子性
     * 如下方，getValue() 方法的确可以加 synchronized 来保证，但就提高了安全性，降低了性能，用 volatile 来减轻这个重锁，让其读的时候轻便些。
     */
    public class Counter{
        private volatile int value;

        public int getValue(){
            return value;   //利用 volatile 保证读取操作的可见性
        }

        public synchronized int inCrement(){
            return value++;    //利用 synchronized 保证复合操作的原子性›
        }
    }
}
