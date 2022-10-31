package DesignPattern.patterns.builder.improve;

/**
 * @date 2022/10/31
 */
public class HighBuilding extends HouseBuilder{
    
    @Override
    public void buildBasic() {
        house.setBasic("100");
        System.out.println("给高楼打地基");
    }

    @Override
    public void buildWall() {
        house.setWall("100");
        System.out.println("给高楼砌墙");
    }

    @Override
    public void roofed() {
        house.setRoofed("100");
        System.out.println("给高楼封顶");
    }
}
