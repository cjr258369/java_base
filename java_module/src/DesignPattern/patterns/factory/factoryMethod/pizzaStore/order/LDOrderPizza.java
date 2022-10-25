package DesignPattern.patterns.factory.factoryMethod.pizzaStore.order;

import DesignPattern.patterns.factory.factoryMethod.pizzaStore.pizza.*;

/**
 * @date 2022/10/25
 */
public class LDOrderPizza extends OrderPizza{

    @Override
    Pizza createPizza(String orderType) {
        System.out.println("使用工厂方法模式");
        Pizza pizza = null;
        if(orderType.equals("cheese")){
            pizza = new LDCheesePizza();
        }else if(orderType.equals("pepper")){
            pizza = new LDPepperPizza();
        }
        return pizza;
    }
}
