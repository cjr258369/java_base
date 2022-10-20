package DesignPattern.patterns.singletonPattern;

/**
 * @date 2022/10/19
 * 
 * 23种设计模式之单例模式：懒汉式（线程安全：双重检查）
 */
public class SingletonTest05 {
    public static void main(String[] args) {
        Signleton5 instance = Signleton5.getInstance();
        Signleton5 instance2 = Signleton5.getInstance();
        System.out.println(instance == instance2);
        System.out.println("instance hashCode = " + instance.hashCode());
        System.out.println("instance2 hashCode = " + instance2.hashCode());
    }
}

class Signleton5{
    //1. 构造器私有化，防止外部 new
    private Signleton5(){
    }
    //2. 本类内部创建对象实例
    private static volatile Signleton5 instance;
    
    //3. 对外提供一个共有的静态方法，当使用到该方法时，才会去创建 instance
    //加入双重检查，去掉方法上的同步修饰，提高效率，仅同步创建实例的代码即可。
    public static Signleton5 getInstance(){
        if(instance == null){
            synchronized (Signleton5.class){
                if(instance == null){
                    instance = new Signleton5();
                }
            }
        }
        return instance;
    }
}
