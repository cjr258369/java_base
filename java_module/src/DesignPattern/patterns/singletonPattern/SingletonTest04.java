package DesignPattern.patterns.singletonPattern;

/**
 * @date 2022/10/19
 * 
 * 23种设计模式之单例模式：懒汉式（线程安全：同步方法）
 */
public class SingletonTest04 {
    public static void main(String[] args) {
        Signleton4 instance = Signleton4.getInstance();
        Signleton4 instance2 = Signleton4.getInstance();
        System.out.println(instance == instance2);
        System.out.println("instance hashCode = " + instance.hashCode());
        System.out.println("instance2 hashCode = " + instance2.hashCode());
    }
}

class Signleton4{
    //1. 构造器私有化，防止外部 new
    private Signleton4(){
    }
    //2. 本类内部创建对象实例
    private static Signleton4 instance;
    
    //3. 对外提供一个共有的静态方法，当使用到该方法时，才会去创建 instance
    //解决线程安全问题，加入了同步 synchronized 修饰，使得同一时间只能有一个线程访问
    public static synchronized Signleton4 getInstance(){
        if(instance == null){
            instance = new Signleton4();
        }
        return instance;
    }
}
