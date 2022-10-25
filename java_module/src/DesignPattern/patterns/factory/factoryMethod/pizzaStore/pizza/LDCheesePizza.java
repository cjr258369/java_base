package DesignPattern.patterns.factory.factoryMethod.pizzaStore.pizza;

/**
 * @date 2022/10/25
 */
public class LDCheesePizza extends Pizza{
    @Override
    public void prepare() {
        setName("伦敦奶酪Pizza");
        System.out.println("给制作伦敦奶酪Pizza 准备原材料");
    }
}
