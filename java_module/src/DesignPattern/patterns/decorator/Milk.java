package DesignPattern.patterns.decorator;

//具体的修饰者（Decorator），这里是调味品
public class Milk extends Decorator{
    public Milk(Drink obj) {
        super(obj);
        setDesc("牛奶");
        setPrice(2.5f);
    }
}
