package DesignPattern.patterns.factory.factoryMethod.pizzaStore.pizza;

/**
 * @date 2022/10/25
 */
public class LDPepperPizza extends Pizza{
    @Override
    public void prepare() {
        setName("伦敦胡椒Pizza");
        System.out.println("给制作伦敦胡椒Pizza 准备原材料");
    }
}
