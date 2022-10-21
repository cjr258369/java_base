package DesignPattern.patterns.factory.simplefactory.pizzaStore.pizza;

/**
 * @date 2022/10/21
 */
public class CheesePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("给制作奶酪Pizza 准备原材料");
    }
}
