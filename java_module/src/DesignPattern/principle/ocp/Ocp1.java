package DesignPattern.principle.ocp;

/**
 * @date 2022/10/14
 * 
 * 设计模式七大原则之：开闭原则【反例】
 * 1、这种方式的优点是：比较好理解，简单易操作
 * 2、缺点是违反了设计模式的OCP原则，即对扩展开放，对修改关闭，即当我们给类增加新功能的时候，尽量不修改代码，或者尽量少修改代码。
 * 3、比如我们这是需要增加一个图形种类，我们需要做如下修改，修改的地方比较多。
 * 4、修改后的代码示例：Ocp2.java
 * 
 */
public class Ocp1 {
    public static void main(String[] args) {
        //使用看下，存在的问题是什么
        GraphicEditer1 graphicEditer1 = new GraphicEditer1();
        graphicEditer1.drawShape(new Rectangle1());
        graphicEditer1.drawShape(new Circle1());
    }
}

//这是一个用于绘图的类
class GraphicEditer1{
    //接收一个 Shape1 图形对象，然后根据 type 来绘制不同的图
    public void drawShape(Shape1 s){
        if(s.m_type == 1){
            drawRectangle(s);
        }else if(s.m_type == 2){
            drawCircle(s);
        }
    }

    private void drawCircle(Shape1 s) {
        System.out.println("绘制：圆形");
    }
    private void drawRectangle(Shape1 s) {
        System.out.println("绘制：矩形");
    }
}

//Shape1 图形的基类
class Shape1{
    int m_type;
}

//Shape1 基类 的两个实现类，在构造时注入不同的类型的值
class Rectangle1 extends Shape1{
    Rectangle1(){
        super.m_type = 1;
    }
}

class Circle1 extends Shape1{
    Circle1(){
        super.m_type = 2;
    }
}
