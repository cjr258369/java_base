package DesignPattern.patterns.factory.absFactory.pizzaStore.order;

import com.sun.org.apache.xpath.internal.operations.Or;

/**
 * @date 2022/10/25
 */
public class PizzaStore {
    public static void main(String[] args) {
        //new OrderPizza1(new BJFactory());
        new OrderPizza1(new LDFactory());
    }
}
