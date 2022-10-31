package DesignPattern.patterns.builder.improve;

/**
 * @date 2022/10/31
 * 
 * 23种设计模式之单例模式：建造者模式【使用建造者模式，解耦产品与制作产品的流程，方便日后扩展产品，或者扩展和变更制作产品的流程】
 */
public class Client {
    public static void main(String[] args) {
        //盖普通房
        CommonHouse commonHouse = new CommonHouse();
        //创建房子的指挥者
        HouseDirector houseDirector = new HouseDirector(commonHouse);
        //完成盖房子，返回产品（房子）
        House house = houseDirector.constructHouse();
        
        //之后可以看这个产品（房子）的具体属性，如地基有多厚，墙有多高
        System.out.println("house 的地基：" + house.getBasic() + " , house 的墙：" + house.getWall());

        System.out.println("=============== 分隔符 ===============");
        //盖高楼
        HighBuilding highBuilding = new HighBuilding();
        //重置指挥者即可
        houseDirector.setHouseBuilder(highBuilding);
        //完成盖高楼
        house = houseDirector.constructHouse();
        System.out.println("house 的地基：" + house.getBasic() + " , house 的墙：" + house.getWall());
    }
}
