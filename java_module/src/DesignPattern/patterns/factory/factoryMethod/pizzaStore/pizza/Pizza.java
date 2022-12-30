package DesignPattern.patterns.factory.factoryMethod.pizzaStore.pizza;

/**
 * @date 2022/10/21
 * 
 * 23种设计模式之工厂方法模式
 * 
 * 将 Pizza 类做成抽象
 */
public abstract class Pizza {
    protected String name;  //Pizza 名字
    //准备原材料，不同Pizza是不同的，因此做成抽象方法，让子类实现
    public abstract void prepare();
    //烘烤
    public void bake(){
        System.out.println(name + " baking;");
    }
    //切割
    public void cut(){
        System.out.println(name + " cutting;");
    }
    //打包
    public void box(){
        System.out.println(name + " boxing;");
    }

    public void setName(String name) {
        this.name = name;
    }
}
