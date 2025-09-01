package DesignPattern.patterns.facade;

public class Projector {

    //继续使用饿汉式
    private static Projector instance = new Projector();

    public static Projector getInstance(){
        return instance;
    }

    public void on(){
        System.out.println(" 投影仪 打开 ");
    }

    public void off(){
        System.out.println(" 投影仪 关闭 ");
    }

    public void focus(){
        System.out.println(" 投影仪 正在聚焦 ");
    }
}
