package DesignPattern.principle.singleResponsibility;

/**
 * 设计模式七大原则之：单一职责【反例】
 * 
 * 分析：
 * 1、Vehicle 的 run 方法中，违反了单一职责原则，比如飞机不应该也是再公路上运行，应该在天上飞
 * 2、解决方案：根据交通工具运行的方式不同，分解为不同的类即可，解决方案：SingleResponsibility2.java
 * @date 2022/10/11
 */
public class SingleResponsibility1 {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle.run("摩托车");
        vehicle.run("汽车");
        vehicle.run("飞机");

    }
}

//交通工具类
class Vehicle{
    public void run(String vehicle){
        System.out.println(vehicle + " 在公路上运行....");
    }
}
