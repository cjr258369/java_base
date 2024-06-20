package DesignPattern.patterns.decorator;

public abstract class Drink {
    //描述
    public String desc;
    
    private float price = 0.0f;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    //计算费用的抽象方法
    public abstract float cost();
}
