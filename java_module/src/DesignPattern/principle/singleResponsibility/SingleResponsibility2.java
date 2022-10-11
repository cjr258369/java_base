package DesignPattern.principle.singleResponsibility;

/**
 * 设计模式七大原则之：单一职责【正例】
 * 
 * 分析：
 * 1、它遵守了单一职责原则
 * 2、但是这样做的改动很大，即将类分解，同时也要修改客户端（也即main方法里面的内容）
 * 3、改进：直接修改原先的 Vehicle 类，这样改动的代码会比较少，方案3：SingleResponsibility3
 * @date 2022/10/11
 */
public class SingleResponsibility2 {
    public static void main(String[] args) {
        RoadVehicle roadVehicle = new RoadVehicle();
        roadVehicle.run("摩托车");
        roadVehicle.run("汽车");

        AirVehicle airVehicle = new AirVehicle();
        airVehicle.run("飞机");

        WaterVehicle waterVehicle = new WaterVehicle();
        waterVehicle.run("轮船");

    }
}

//陆地上跑的交通工具类
class RoadVehicle{
    public void run(String vehicle){
        System.out.println(vehicle + " 在公路上运行....");
    }
}

//天空上跑的交通工具类
class AirVehicle{
    public void run(String vehicle){
        System.out.println(vehicle + " 在天空上运行....");
    }
}

//水上跑的交通工具类
class WaterVehicle{
    public void run(String vehicle){
        System.out.println(vehicle + " 在水上运行....");
    }
}
