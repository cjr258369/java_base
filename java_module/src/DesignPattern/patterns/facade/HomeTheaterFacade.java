package DesignPattern.patterns.facade;

public class HomeTheaterFacade {
    
    //定义各个子系统的对象
    private TheaterLight theaterLight;
    private PopCorn popCorn;
    private Projector projector;
    private Screen screen;
    private DVDPlayer dvdPlayer;
    private Stereo stereo;

    public HomeTheaterFacade() {
        this.theaterLight = TheaterLight.getInstance();
        this.popCorn = PopCorn.getInstance();
        this.projector = Projector.getInstance();
        this.screen = Screen.getInstance();
        this.dvdPlayer = DVDPlayer.getInstance();
        this.stereo = Stereo.getInstance();
    }
    
    //操作分成 4 步
    public void ready(){
        popCorn.on();
        popCorn.pop();
        screen.down();
        projector.on();
        stereo.on();
        dvdPlayer.on();
        theaterLight.dim();
    }
    
    public void play(){
        dvdPlayer.play();
    }
    
    public void pause(){
        dvdPlayer.pause();
    }
    
    public void end(){
        popCorn.off();
        theaterLight.bright();
        screen.up();
        projector.off();
        stereo.off();
        dvdPlayer.off();
    }
}
