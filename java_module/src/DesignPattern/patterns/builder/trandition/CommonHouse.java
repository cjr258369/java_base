package DesignPattern.patterns.builder.trandition;

/**
 * @date 2022/10/31
 */
public class CommonHouse extends AbstractHouse{
    @Override
    public void buildBase() {
        System.out.println("给普通房子打地基");
    }

    @Override
    public void buildWalls() {
        System.out.println("给普通房子砌墙");
    }

    @Override
    public void roofed() {
        System.out.println("给普通房子封顶");
    }
}
