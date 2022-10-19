package DesignPattern.uml.aggregation;

/**
 * @date 2022/10/19
 */
public class Computer {
    private Mouse mouse;    //鼠标可以和 Computer 分离
    private Monitor monitor;    //显示器可以和 Computer 分离

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }
}
