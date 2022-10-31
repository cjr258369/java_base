package DesignPattern.patterns.builder.improve;

/**
 * @date 2022/10/31
 * 
 * 抽象的建造者
 */
public abstract class HouseBuilder {
    
    protected House house = new House();
    
    //将建造的流程写好，抽象的方法
    public abstract void buildBasic();
    public abstract void buildWall();
    public abstract void roofed();
    
    //使用前面的流程建房，把建好的房子放回去，房子建好后，将产品（房子）返回
    public House buildHouse(){
        return house;        
    }
}
