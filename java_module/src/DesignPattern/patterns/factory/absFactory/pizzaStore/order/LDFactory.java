package DesignPattern.patterns.factory.absFactory.pizzaStore.order;

import DesignPattern.patterns.factory.absFactory.pizzaStore.pizza.*;

/**
 * @date 2022/10/25
 * 
 * 23种设计模式之单例模式：抽象工厂模式
 * 
 * 工厂子类
 */
public class LDFactory implements AbsFactory{
    @Override
    public Pizza createPizza(String orderType) {
        System.out.println("使用抽象工厂模式");
        Pizza pizza = null;
        if(orderType.equals("cheese")){
            pizza = new LDCheesePizza();
        }else if(orderType.equals("pepper")){
            pizza = new LDPepperPizza();
        }
        return pizza;
    }
}
