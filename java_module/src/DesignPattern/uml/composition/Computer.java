package DesignPattern.uml.composition;

/**
 * @date 2022/10/19
 */
public class Computer {
    private Mouse mouse = new Mouse();    //鼠标和 Computer 不可分离，与Computer 同生共死
    private Monitor monitor = new Monitor();    //显示器可以和 Computer 不可分离，与Computer 同生共死
}
