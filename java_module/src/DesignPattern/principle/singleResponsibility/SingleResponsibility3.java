package DesignPattern.principle.singleResponsibility;

/**
 * 设计模式七大原则之：单一职责【正例】
 *
 * 分析：
 * 1、这种修改方法，没有对原来的类做大的修改，只是增加了方法
 * 2、它虽然没有在类这个级别上遵守单一职责原则，但是在方法这个级别上，仍然是遵守单一职责原则的。
 * @date 2022/10/11
 */
public class SingleResponsibility3 {
    public static void main(String[] args) {
        Vehicle2 vehicle2 = new Vehicle2();
        vehicle2.run("摩托车");
        vehicle2.run("汽车");
        vehicle2.runAir("飞机");
        vehicle2.runWater("轮船");
    }
}

class Vehicle2{
    public void run(String vehicle){
        System.out.println(vehicle + " 在公路上运行......");
    }
    public void runAir(String vehicle){
        System.out.println(vehicle + " 在天空上运行......");
    }
    public void runWater(String vehicle){
        System.out.println(vehicle + " 在水中上运行......");
    }
}