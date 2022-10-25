package DesignPattern.patterns.factory.factoryMethod.pizzaStore.pizza;

/**
 * @date 2022/10/25
 */
public class BJPepperPizza extends Pizza{
    @Override
    public void prepare() {
        setName("北京胡椒Pizza");
        System.out.println("给制作北京胡椒Pizza 准备原材料");
    }
}
