package DesignPattern.patterns.factory.simplefactory.pizzaStore.improve1_simpleFactory;

import DesignPattern.patterns.factory.simplefactory.pizzaStore.pizza.CheesePizza;
import DesignPattern.patterns.factory.simplefactory.pizzaStore.pizza.GreekPizza;
import DesignPattern.patterns.factory.simplefactory.pizzaStore.pizza.PepperPizza;
import DesignPattern.patterns.factory.simplefactory.pizzaStore.pizza.Pizza;

/**
 * @date 2022/10/21
 * 23种设计模式之简单工厂模式
 * 简单工厂类
 */
public class SimpleFactory {
    //根据 orderType 返回对应的 Pizza 实例
    public Pizza createPizza(String orderType){
        System.out.println("使用简单工厂模式");
        Pizza pizza = null;
        if(orderType.equals("greek")){
            pizza = new GreekPizza();
            pizza.setName(orderType);
        }else if(orderType.equals("cheese")){
            pizza = new CheesePizza();
            pizza.setName(orderType);
        }else if(orderType.equals("pepper")){
            pizza = new PepperPizza();
            pizza.setName(orderType);
        }
        
        return pizza;
    }
    
    //简单工厂模式，也叫静态工厂模式，该方法改为静态的也行
    public static Pizza createPizza2(String orderType){
        System.out.println("使用简单工厂模式");
        Pizza pizza = null;
        if(orderType.equals("greek")){
            pizza = new GreekPizza();
            pizza.setName(orderType);
        }else if(orderType.equals("cheese")){
            pizza = new CheesePizza();
            pizza.setName(orderType);
        }else if(orderType.equals("pepper")){
            pizza = new PepperPizza();
            pizza.setName(orderType);
        }

        return pizza;
    }
}
