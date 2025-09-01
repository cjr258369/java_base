package DesignPattern.patterns.facade;

public class Client {
    //如果不使用外观模式，直接调用的话很麻烦，扩展也不方便
    
    
    //使用外观模式
    public static void main(String[] args) {
        HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade();

        homeTheaterFacade.ready();
        homeTheaterFacade.play();


        homeTheaterFacade.pause();

        homeTheaterFacade.end();
    }
}
