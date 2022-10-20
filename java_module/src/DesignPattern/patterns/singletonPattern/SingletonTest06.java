package DesignPattern.patterns.singletonPattern;

/**
 * @date 2022/10/19
 * 
 * 23种设计模式之单例模式：静态内部类
 */
public class SingletonTest06 {
    public static void main(String[] args) {
        Signleton6 instance = Signleton6.getInstance();
        Signleton6 instance2 = Signleton6.getInstance();
        System.out.println(instance == instance2);
        System.out.println("instance hashCode = " + instance.hashCode());
        System.out.println("instance2 hashCode = " + instance2.hashCode());
    }
}

class Signleton6{
    //1. 构造器私有化，防止外部 new
    private Signleton6(){
    }
    
    //2. 编写一个静态内部类，该类中有一个静态属性：Signleton6
    private static class SingletonInstance{
        private static final Signleton6 INSTANCE = new Signleton6();
    }
    
    
    //3. 对外提供一个共有的静态方法，当使用到该方法时，才会去创建 instance
    public static Signleton6 getInstance(){
        return SingletonInstance.INSTANCE;
    }
}
