package DesignPattern.patterns.factory.factoryMethod.pizzaStore.order;

/**
 * @date 2022/10/25
 * 
 * 23种设计模式之工厂方法模式
 * 使用
 */
public class PizzaStore {
    public static void main(String[] args) {
        //获取入参或输入
        //String loc = "北京";
        String loc = "伦敦";
        if(loc.equals("北京")){
            //创建北京口味的各种Pizza
            new BJOrderPizza();
        }else{
            //创建伦敦口味的各种Pizza
            new LDOrderPizza();
        }
    }
}
