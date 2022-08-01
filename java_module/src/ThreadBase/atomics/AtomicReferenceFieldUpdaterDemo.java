package ThreadBase.atomics;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 多线程并发调用一个类的初始化方法，如果违背初始化时，将执行初始化工作
 * 要求只能被初始化一次，只有一个线程操作成功
 *
 * @author chenjunran
 * @date 2022/8/1
 */
public class AtomicReferenceFieldUpdaterDemo {
    public static void main(String[] args) {
        MyVar myVar = new MyVar();
        for(int i = 1; i <= 7; i++){
            new Thread(() -> {
                myVar.init(myVar);
            }, String.valueOf(i)).start();
        }
    }
}

class MyVar{
    public volatile Boolean isInit = Boolean.FALSE;

    //静态构造方法的 3个参数：哪个类，什么类型，哪个字段
    AtomicReferenceFieldUpdater<MyVar, Boolean> referenceFieldUpdater =
            AtomicReferenceFieldUpdater.newUpdater(MyVar.class, Boolean.class, "isInit");

    public void init(MyVar myVar){
        //这里并不用 while ，所有线程有一个完成即可。
        if (referenceFieldUpdater.compareAndSet(myVar, Boolean.FALSE, Boolean.TRUE)) {
            System.out.println(Thread.currentThread().getName() + "\t ---- start init，需要 3 秒");
            //暂停几秒钟线程
            try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + "\t ---- init 完成");
        }else{
            System.out.println(Thread.currentThread().getName() + "\t ---- 已经有线程在执行初始化工作");
        }
    }
}
