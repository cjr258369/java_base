package DesignPattern.patterns.builder.trandition;

/**
 * @date 2022/10/31
 * 
 * 23种设计模式之单例模式：建造者模式【传统思路】
 */
public abstract class AbstractHouse {
    //打地基
    public abstract void buildBase();
    //砌墙
    public abstract void buildWalls();
    //封顶
    public abstract void roofed();
    
    //因为需要有序执行，所以封装到同一个方法内
    public void build(){
        buildBase();
        buildWalls();
        roofed();
    }
}
