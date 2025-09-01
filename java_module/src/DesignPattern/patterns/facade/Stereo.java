package DesignPattern.patterns.facade;

public class Stereo {

    //继续使用饿汉式
    private static Stereo instance = new Stereo();

    public static Stereo getInstance(){
        return instance;
    }

    public void on(){
        System.out.println(" 立体声 打开 ");
    }

    public void off(){
        System.out.println(" 立体声 关闭 ");
    }
    
    public void up(){
        System.out.println(" 立体声 音量调大 ");
    }

    public void down(){
        System.out.println(" 立体声 音量调小 ");
    }
}
