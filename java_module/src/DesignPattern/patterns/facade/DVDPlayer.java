package DesignPattern.patterns.facade;

public class DVDPlayer {
    
    //使用单例模式，使用饿汉式
    private static DVDPlayer instance = new DVDPlayer();
    
    public static DVDPlayer getInstance(){
        return instance;
    }
    
    //打开的功能
    public void on(){
        System.out.println(" DVD 打开 ");
    }
    
    public void off(){
        System.out.println(" DVD 关闭 ");
    }
    
    public void play(){
        System.out.println(" DVD 正在播放 ");
    }
    
    public void pause(){
        System.out.println(" DVD 暂停了 ");
    }
    
}
