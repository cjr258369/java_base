package DesignPattern.patterns.factory.absFactory.pizzaStore.order;

import DesignPattern.patterns.factory.absFactory.pizzaStore.pizza.BJCheesePizza;
import DesignPattern.patterns.factory.absFactory.pizzaStore.pizza.BJPepperPizza;
import DesignPattern.patterns.factory.absFactory.pizzaStore.pizza.Pizza;

/**
 * @date 2022/10/25
 * 
 * 23种设计模式之抽象工厂模式
 * 
 * 工厂子类
 */
public class BJFactory implements AbsFactory{
    @Override
    public Pizza createPizza(String orderType) {
        System.out.println("使用抽象工厂模式");
        Pizza pizza = null;
        if(orderType.equals("cheese")){
            pizza = new BJCheesePizza();
        }else if(orderType.equals("pepper")){
            pizza = new BJPepperPizza();
        }
        return pizza;
    }
}
