package DesignPattern.patterns.decorator;

//具体的修饰者（Decorator），这里是调味品
public class Chocolate extends Decorator{
    public Chocolate(Drink obj) {
        super(obj);
        setDesc("巧克力");
        setPrice(4.5f);
    }
}
