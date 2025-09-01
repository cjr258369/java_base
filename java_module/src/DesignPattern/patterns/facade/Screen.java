package DesignPattern.patterns.facade;

public class Screen {

    //继续使用饿汉式
    private static Screen instance = new Screen();

    public static Screen getInstance(){
        return instance;
    }

    public void up(){
        System.out.println(" 屏幕 上升 ");
    }

    public void down(){
        System.out.println(" 屏幕 下降 ");
    }
}
