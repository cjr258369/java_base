package DesignPattern.patterns.factory.simplefactory.pizzaStore.improve1_simpleFactory;

/**
 * @date 2022/10/21
 * 
 * 相当于一个客户端，发起Pizza的订购任务
 */
public class PizzaStore {
    public static void main(String[] args) {
        //使用简单工厂模式
        //new OrderPizza(new SimpleFactory());
        //使用静态工厂时的调用
        new OrderPizza2();
        System.out.println("======退出程序======");
    }
}
