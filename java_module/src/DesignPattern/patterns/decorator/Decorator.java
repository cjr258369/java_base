package DesignPattern.patterns.decorator;

public class Decorator extends Drink{
    private Drink obj;

    public Decorator(Drink obj) {
        this.obj = obj;
    }
    
    @Override
    public float cost() {
        //super.getPrice() 自己调料的价格
        //obj.cost() 饮料的价格
        return super.getPrice() + obj.cost();
    }

    @Override
    public String getDesc() {
        //obj.getDesc() 被装饰者的信息
        return super.getDesc() + "_" + super.getPrice() + " + " + obj.getDesc();
    }
}
