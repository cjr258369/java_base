package DesignPattern.principle.segregation.improve;

/**
 * @date 2022/10/12
 * 
 * 设计模式七大原则之：接口隔离【正例】
 * 1、将接口 Interface1 拆分为独立的几个接口，类A 和 类C 分别与他们需要的接口建立依赖关系，也就是采用接口隔离原则。
 */
public class IntegerfaceSegragation2 {
    //使用一下
    public static void main(String[] args) {
        A a = new A();
        a.depend1(new B()); //A 类通过接口去依赖（使用）B类
        a.depend2(new B());
        a.depend3(new B());
        
        C c = new C();
        c.depend1(new D()); //C 类通过接口去依赖（使用）D类
        c.depend4(new D());
        c.depend5(new D());
    }
}

//接口1
interface Interface1{
    void operation1();
}

interface Interface2{
    void operation2();
    void operation3();
}

interface Interface3{
    void operation4();
    void operation5();
}

class B implements Interface1,Interface2{
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
}

class D implements Interface1,Interface3{
    @Override
    public void operation1() {
        System.out.println("D 中实现的 operation1");
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

//A 类通过接口 Interface1,Interface2 依赖（使用）B类，但是只会用到1，2,2，3 方法
class A{
    public void depend1(Interface1 a){
        a.operation1();        
    }
    public void depend2(Interface2 a){
        a.operation2();        
    }
    public void depend3(Interface2 a){
        a.operation3();        
    }
}

//C 类通过接口 Interface1,Interface3 依赖（使用）D类，但是只会用到1，4，5 方法
class C{
    public void depend1(Interface1 c){
        c.operation1();        
    }
    public void depend4(Interface3 c){
        c.operation4();        
    }
    public void depend5(Interface3 c){
        c.operation5();        
    }
}
