package DesignPattern.patterns.bridge;

/**
 * @date 2023/1/3
 */
public abstract class AbsPhone {
    //组合品牌
    private Brand brand;

    public AbsPhone(Brand brand) {
        this.brand = brand;
    }
    
    protected void open(){
        brand.open();
    }
    
    protected void close(){
        brand.close();
    }
    
    protected void call(){
        brand.call();
    }
}
