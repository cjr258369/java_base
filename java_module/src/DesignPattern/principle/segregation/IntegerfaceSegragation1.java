package DesignPattern.principle.segregation;

/**
 * @date 2022/10/12
 * 
 * 设计模式七大原则之：接口隔离【反例】
 * 1、类B 和 类D，都实现了相同的接口，但实际B类不需要实现接口里的4，5 这两个方法，D类也不需要多余的实现2，3这两个方法
 */
public class IntegerfaceSegragation1 {
}

interface Interface1{
    void operation1();
    void operation2();
    void operation3();
    void operation4();
    void operation5();
}

class B implements Interface1{
    @Override
    public void operation1() {
        System.out.println("B 中实现的 operation1");
    }
    @Override
    public void operation2() {
        System.out.println("B 中实现的 operation2");
    }
    @Override
    public void operation3() {
        System.out.println("B 中实现的 operation3");
    }
    @Override
    public void operation4() {
        System.out.println("B 中实现的 operation4");
    }
    @Override
    public void operation5() {
        System.out.println("B 中实现的 operation5");
    }
}

class D implements Interface1{
    @Override
    public void operation1() {
        System.out.println("D 中实现的 operation1");
    }
    @Override
    public void operation2() {
        System.out.println("D 中实现的 operation2");
    }
    @Override
    public void operation3() {
        System.out.println("D 中实现的 operation3");
    }
    @Override
    public void operation4() {
        System.out.println("D 中实现的 operation4");
    }
    @Override
    public void operation5() {
        System.out.println("D 中实现的 operation5");
    }
}

//A 类通过接口 Interface1 依赖（使用）B类，但是只会用到1，2,2，3 方法
class A{
    public void depend1(Interface1 a){
        a.operation1();        
    }
    public void depend2(Interface1 a){
        a.operation2();        
    }
    public void depend3(Interface1 a){
        a.operation3();        
    }
}

//C 类通过接口 Interface1 依赖（使用）D类，但是只会用到1，4，5 方法
class C{
    public void depend1(Interface1 c){
        c.operation1();        
    }
    public void depend4(Interface1 c){
        c.operation4();        
    }
    public void depend5(Interface1 c){
        c.operation5();        
    }
}
