package DesignPattern.patterns.adapter.classAdapter;

/**
 * @date 2022/11/1
 * 
 * 23种设计模式之适配器模式：类适配器
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("类适配器模式");
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter());
    }
}
