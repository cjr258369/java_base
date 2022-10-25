package DesignPattern.patterns.factory.factoryMethod.pizzaStore.order;

import DesignPattern.patterns.factory.factoryMethod.pizzaStore.pizza.BJCheesePizza;
import DesignPattern.patterns.factory.factoryMethod.pizzaStore.pizza.BJPepperPizza;
import DesignPattern.patterns.factory.factoryMethod.pizzaStore.pizza.Pizza;

/**
 * @date 2022/10/25
 */
public class BJOrderPizza extends OrderPizza{

    @Override
    Pizza createPizza(String orderType) {
        System.out.println("使用工厂方法模式");
        Pizza pizza = null;
        if(orderType.equals("cheese")){
            pizza = new BJCheesePizza();
        }else if(orderType.equals("pepper")){
            pizza = new BJPepperPizza();
        }
        return pizza;
    }
}
