package DesignPattern.patterns.singletonPattern;

/**
 * @date 2022/10/19
 * 
 * 23种设计模式之单例模式：饿汉式（静态代码块）
 */
public class SingletonTest02 {
    public static void main(String[] args) {
        Signleton2 instance = Signleton2.getInstance();
        Signleton2 instance2 = Signleton2.getInstance();
        System.out.println(instance == instance2);
        System.out.println("instance hashCode = " + instance.hashCode());
        System.out.println("instance2 hashCode = " + instance2.hashCode());
    }
}

class Signleton2{
    //1. 构造器私有化，防止外部 new
    private Signleton2(){
    }
    //2. 本类内部创建对象实例
    private static Signleton2 instance;

    //在静态代码块中，创建单例对象
    static{
        instance = new Signleton2();
    }
    
    //3. 对外提供一个共有的静态方法，返回实例对象
    public static Signleton2 getInstance(){
        return instance;
    }
}
