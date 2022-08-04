package ThreadBase.tl;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ReferenceDemo {
    public static void main(String[] args) {

    }

    private static void phantomReference() {
        //这个 Demo 的效果 ，也需要像软引用那样，设置：-Xms10m -Xmx10m，而且不一定能完整复现出效果，可能要多试几次
        MyObject myObject = new MyObject();
        ReferenceQueue<MyObject> myObjectReferenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomReference = new PhantomReference(myObject, myObjectReferenceQueue);

        //验证虚引用的 get 方法只会返回 null
        //System.out.println(phantomReference.get());

        List<byte[]> list = new ArrayList<>();

        new Thread(() -> {
            while(true){
                //模拟不停地往 list 里面 1M，1M 的添加数据
                list.add(new byte[1 * 1024 * 1024]);
                //模拟内存被撑满时，JVM 自动触发 GC 时，虚引用对象会有哪些情况
                //暂停毫秒
                try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
                //每 500 毫秒，观察一次
                System.out.println(phantomReference.get() + "\t list add ok");
            }
        }, "t1").start();

        new Thread(() -> {
            while(true){
                //看看 虚引用队列里面有没有值，队列是什么情况
                Reference<? extends MyObject> reference = myObjectReferenceQueue.poll();
                if(reference != null){
                    System.out.println("---- 有虚对象被干掉了，放入了队列");
                    break;
                }
            }
        }, "t2").start();
    }

    //弱引用，不管内存够不够，都回收
    private static void weakReference() {
        WeakReference<MyObject> myObjectWeakReference = new WeakReference<>(new MyObject());
        System.out.println(" ---- gc before " + myObjectWeakReference.get());
        System.gc();
        //暂停几秒钟线程
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        System.out.println(" ---- gc after " + myObjectWeakReference.get());
    }

    //软引用：内存足够不回收，内存不够，就回收
    private static void softReference() {
        SoftReference<MyObject> myObjectSoftReference = new SoftReference<>(new MyObject());
        //System.out.println(" ------ myObjectSoftReference："+myObjectSoftReference.get());
        System.gc();
        //暂停几秒钟线程
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("---- gc after 内存够用："+myObjectSoftReference.get());

        //在 Build and run (构建并运行) 的右边 Modify options(修改选项)，选择 Add VM options(添加 VM 选项)
        //在 弹出来的选项框里面输入：-Xms10m -Xmx10m
        //使得最大运行只支持 10M

        try {
            //造一个 20M 的对象
            byte[] bytes = new byte[20 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //就算不置为 null ,垃圾回收器也照样回收，因为内存不够了，且 它不是强引用
            System.out.println("---- gc after 内存不够用："+myObjectSoftReference.get());
        }
    }

    //强引用 Demo, 就算oom都不回收
    private static void strongReference() {
        MyObject myObject = new MyObject();
        System.out.println("gc before： "+myObject);

        myObject = null;
        //人工开启GC，正常代码不需要写，由垃圾回收器处理。
        System.gc();
        //暂停几秒钟线程
        //try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

        System.out.println("gc after： "+myObject);
    }
}

class MyObject{
    //这个方法一般不用复写，这里只是做演示 Demo，用来了解 强、软、弱、虚 四大引用

    @Override
    protected void finalize() throws Throwable {
        //finalize的通常目的是在对象被不可撤销地丢弃之前执行清理操作
        System.out.println("---- invoke finalize method~~~");
    }
}
