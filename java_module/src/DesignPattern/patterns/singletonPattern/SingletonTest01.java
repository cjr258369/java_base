package DesignPattern.patterns.singletonPattern;

/**
 * @date 2022/10/19
 * 
 * 23中设计模式之单例模式：饿汉式（静态常量）
 */
public class SingletonTest01 {
    public static void main(String[] args) {
        Signleton1 instance = Signleton1.getInstance();
        Signleton1 instance2 = Signleton1.getInstance();
        System.out.println(instance == instance2);
        System.out.println("instance hashCode = " + instance.hashCode());
        System.out.println("instance2 hashCode = " + instance2.hashCode());
    }
}

class Signleton1{
    //1. 构造器私有化，防止外部 new
    private Signleton1(){
    }
    
    //2. 本类内部创建对象实例
    private final static Signleton1 instance = new Signleton1();
    
    //3. 对外提供一个共有的静态方法，返回实例对象
    public static Signleton1 getInstance(){
        return instance;
    }
}
