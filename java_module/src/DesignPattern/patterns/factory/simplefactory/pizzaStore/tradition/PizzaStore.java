package DesignPattern.patterns.factory.simplefactory.pizzaStore.tradition;

/**
 * @date 2022/10/21
 * 
 * 23种设计模式之单例模式：简单工厂模式【传统写法】
 * 相当于一个客户端，发起Pizza的订购任务
 */
public class PizzaStore {
    public static void main(String[] args) {
        new OrderPizza();
    }
}
