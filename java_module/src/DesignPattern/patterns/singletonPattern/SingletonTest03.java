package DesignPattern.patterns.singletonPattern;

/**
 * @date 2022/10/19
 * 
 * 23种设计模式之单例模式：懒汉式（线程不安全）
 */
public class SingletonTest03 {
    public static void main(String[] args) {
        Signleton3 instance = Signleton3.getInstance();
        Signleton3 instance2 = Signleton3.getInstance();
        System.out.println(instance == instance2);
        System.out.println("instance hashCode = " + instance.hashCode());
        System.out.println("instance2 hashCode = " + instance2.hashCode());
    }
}

class Signleton3{
    //1. 构造器私有化，防止外部 new
    private Signleton3(){
    }
    //2. 本类内部创建对象实例
    private static Signleton3 instance;
    
    //3. 对外提供一个共有的静态方法，当使用到该方法时，才会去创建 instance
    public static Signleton3 getInstance(){
        if(instance == null){
            instance = new Signleton3();
        }
        return instance;
    }
}
