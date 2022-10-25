package DesignPattern.patterns.factory.absFactory.pizzaStore.pizza;

/**
 * @date 2022/10/25
 */
public class BJCheesePizza extends Pizza {
    @Override
    public void prepare() {
        setName("北京奶酪Pizza");
        System.out.println("给制作北京奶酪Pizza 准备原材料");
    }
}
