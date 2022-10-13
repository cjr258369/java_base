package DesignPattern.principle.liskov;

/**
 * @date 2022/10/13
 * 
 * 设计模式七大原则之：里氏替换原则【正例】
 * 改进：采取一个更加通俗的基类，如果B类还需要用带A类，那么通过依赖，聚合，组合等手法来使用
 * 
 */
public class Liskov2 {
    public static void main(String[] args) {
        A2 a = new A2();
        System.out.println("10 - 3 = " + a.func1(10, 3));
        System.out.println("1 - 8 = " + a.func1(1, 8));

        System.out.println("--------------------");
        B2 b = new B2();
        //因为 B类 不再继承 A类，因此调用者不再认为B类的 func1() 是求减法
        //调用完成的功能就会很明确
        System.out.println("10 + 3 = " + b.func1(10, 3));
        System.out.println("1 + 8 = " + b.func1(1, 8));
        System.out.println("7 + 3 + 9 = " + b.func2(7, 3));
        //使用组合仍然可以使用到 A类 的方法
        System.out.println("10 - 3 = " + b.func3(10, 3));
    }
}

//创建一个更加基础的基类
class Base1{
    //把更加基础的方法和成员写到 Base 类。
}

//A类
class A2 extends Base1{
    //返回两个数的差
    public int func1(int num1, int num2){
        return num1 - num2;
    }
}

//B类继承自 A类，增加了一个新功能：完成了两个数的相加，然后和 9 求和
class B2 extends Base1{
    //组合的方式：如果B类需要使用 A类 的方法
    private A2 a = new A2();
    public int func1(int num1, int num2){
        return num1 + num2;
    }
    public int func2(int num1, int num2){
        return func1(num1, num2) + 9;
    }
    //加入我们仍然想使用 A  的方法
    public int func3(int num1, int num2){
        return a.func1(num1, num2);
    }
}
