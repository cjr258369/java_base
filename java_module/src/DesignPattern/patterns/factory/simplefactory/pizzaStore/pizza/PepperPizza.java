package DesignPattern.patterns.factory.simplefactory.pizzaStore.pizza;

/**
 * @date 2022/10/21
 */
public class PepperPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("给制作胡椒Pizza 准备原材料");
    }
}
