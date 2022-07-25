package ThreadBase.volatiles;

public class SafeDoubleCheckSingleton {

    //未加 volatile
    //private static SafeDoubleCheckSingleton singleton;
    //加了 volatile 声明，实现线程安全的延迟初始化
    private volatile static SafeDoubleCheckSingleton singleton;
    //私有化构造方法
    private SafeDoubleCheckSingleton(){

    }

    public static SafeDoubleCheckSingleton getInstance(){
        if(singleton == null){
            //多线程并发创建对象时，会通过加锁保证只有一个线程能创建对象
            synchronized (SafeDoubleCheckSingleton.class){
                //双重判定
                if(singleton == null){
                    //隐患：多线程环境下，由于重排序，该对象可能还未完成初始化就被其他线程读取，所以应该加上 volatile 防止重排序，详细源码分析见笔记
                    singleton = new SafeDoubleCheckSingleton();
                }
            }
        }
        //对象创建完毕，执行 getInstance() 将不需要获取锁，直接返回创建的对象
        return singleton;
    }
}
