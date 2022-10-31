package DesignPattern.patterns.builder.improve;

/**
 * @date 2022/10/31
 */
public class CommonHouse extends HouseBuilder{

    @Override
    public void buildBasic() {
        house.setBasic("20");
        System.out.println("给普通房子打地基");
    }

    @Override
    public void buildWall() {
        house.setWall("20");
        System.out.println("给普通房子砌墙");
    }

    @Override
    public void roofed() {
        house.setRoofed("20");
        System.out.println("给普通房子封顶");
    }
}
