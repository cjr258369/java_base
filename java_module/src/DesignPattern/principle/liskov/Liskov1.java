package DesignPattern.principle.liskov;

/**
 * @date 2022/10/13
 * 
 * 设计模式七大原则之：里氏替换原则【反例】
 * 
 */
public class Liskov1 {
    public static void main(String[] args) {
        A1 a = new A1();
        System.out.println("10 - 3 = " + a.func1(10, 3));
        System.out.println("1 - 8 = " + a.func1(1, 8));

        System.out.println("--------------------");
        B1 b = new B1();
        System.out.println("10 - 3 = " + b.func1(10, 3));   //代码原本的本意是求差值，因为是想调父类的方法
        System.out.println("1 - 8 = " + b.func1(1, 8));
        System.out.println("7 + 3 + 9 = " + b.func2(7, 3));
    }
}

//A类：父类
class A1{
    //返回两个数的差
    public int func1(int num1, int num2){
        return num1 - num2;
    }
}

//B类继承自 A类，增加了一个新功能：完成了两个数的相加，然后和 9 求和
class B1 extends A1{
    //无意中重写了父类的 func1，导致如果有实例对象想调用父类的方法时，产生错误的结果
    public int func1(int num1, int num2){
        return num1 + num2;
    }
    public int func2(int num1, int num2){
        return func1(num1, num2) + 9;
    }
}
