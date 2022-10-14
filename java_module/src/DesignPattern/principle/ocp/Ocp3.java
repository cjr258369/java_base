package DesignPattern.principle.ocp;

/**
 * @date 2022/10/14
 *
 * 设计模式七大原则之：开闭原则【正例】
 * 改进思路：把创建 Shape类 做成抽象类，并提供一个抽象的 draw 方法，让子类去实现即可，
 * 这样我们又新的图形种类时，只需要让新的图形类继承 Shape，并实现 draw 方法即可。
 * 这样使用方的代码就不需要修改，满足了开闭原则。
 *
 */
public class Ocp3 {
    public static void main(String[] args) {
        GraphicEditer3 graphicEditer = new GraphicEditer3();
        graphicEditer.drawShape(new Rectangle3());
        graphicEditer.drawShape(new Circle3());
        graphicEditer.drawShape(new Triangle3());
        graphicEditer.drawShape(new OtherGraphic1());
    }
}

//这是一个用于绘图的类[使用方]
class GraphicEditer3{
    //接收一个 Shape1 图形对象，然后根据 type 来绘制不同的图
    public void drawShape(Shape3 s){
        s.draw();
    }
}

//Shape1 图形的基类
abstract class Shape3{
    //抽象方法
    public abstract void draw();
}

//Shape1 基类 的两个实现类，在构造时注入不同的类型的值
class Rectangle3 extends Shape3{

    @Override
    public void draw() {
        System.out.println("绘制：矩形");
    }
}

class Circle3 extends Shape3{
    @Override
    public void draw() {
        System.out.println("绘制：圆形");
    }
}

//新增画三角形
class Triangle3 extends Shape3{
    @Override
    public void draw() {
        System.out.println("绘制：三角形");
    }
}

//此时新增功能，对于扩展是开放的，不影响原有功能，新增新的实现类，使用方 GraphicEditer3 无需修改
class OtherGraphic1 extends Shape3{
    @Override
    public void draw() {
        System.out.println("绘制：其他图形");        
    }
}
