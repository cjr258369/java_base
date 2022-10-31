package DesignPattern.patterns.builder.improve;

/**
 * @date 2022/10/31
 * 
 * 指挥者：这里动态的去指定制作流程，返回产品，将 产品和制作流程彻底解耦。
 */
public class HouseDirector {
    HouseBuilder houseBuilder = null;
    
    //方式一：通过构造器 传入 houseBuilder
    public HouseDirector(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    //方式二：通过 setter 传入 houseBuilder
    public void setHouseBuilder(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }
    
    //指挥如何处理建造房子，由指挥者来处理，不同的房子，可以设定不同的建造流程
    public House constructHouse(){
        houseBuilder.buildBasic();
        houseBuilder.buildWall();
        houseBuilder.roofed();
        
        return houseBuilder.buildHouse();
    }
}
