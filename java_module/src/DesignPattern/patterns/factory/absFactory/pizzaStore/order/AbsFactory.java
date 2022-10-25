package DesignPattern.patterns.factory.absFactory.pizzaStore.order;

import DesignPattern.patterns.factory.absFactory.pizzaStore.pizza.Pizza;

/**
 * @date 2022/10/25
 * 
 * 23种设计模式之单例模式：抽象工厂模式
 * 
 * 抽象工厂模式的抽象层（接口）
 */
public interface AbsFactory {
    //让下面的工厂子类来具体实现
    Pizza createPizza(String orderType);
}
