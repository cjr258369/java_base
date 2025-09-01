package DesignPattern.patterns.facade;

public class PopCorn {
    
    //继续使用饿汉式
    private static PopCorn instance = new PopCorn();
    
    public static PopCorn getInstance(){
        return instance;
    }
    
    public void on(){
        System.out.println(" 爆米花机 打开 ");
    }

    public void off(){
        System.out.println(" 爆米花机 关闭 ");
    }

    public void pop(){
        System.out.println(" 爆米花机 正在出爆米花 ");
    }
}
