package DesignPattern.patterns.decorator;

//具体的修饰者（Decorator），这里是调味品
public class Soy extends Decorator{
    public Soy(Drink obj) {
        super(obj);
        setDesc("豆浆");
        setPrice(1.5f);
    }
}
