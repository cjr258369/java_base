package DesignPattern.patterns.singletonPattern;

/**
 * @date 2022/10/19
 * 
 * 23种设计模式之单例模式：枚举实现
 */
public class SingletonTest07 {
    public static void main(String[] args) {
        //先验证是否是单例
        Singleton7 instance = Singleton7.INSTANCE;
        Singleton7 instance2 = Singleton7.INSTANCE;
        System.out.println(instance == instance2);
        System.out.println("instance hashCode = " + instance.hashCode());
        System.out.println("instance2 hashCode = " + instance2.hashCode());
        
        //验证通过实例，调用它的方法：
        instance.sayOK();
        instance2.sayOK();
    }
}

enum Singleton7{
    INSTANCE;
    
    public void sayOK(){
        System.out.println("ok");
    }
}
