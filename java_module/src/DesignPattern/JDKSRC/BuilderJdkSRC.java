package DesignPattern.JDKSRC;

/**
 * @date 2022/10/31
 * 
 * 在 JDK 中，StringBuilder 使用了建造者模式
 */
public class BuilderJdkSRC {
    public static void main(String[] args) {
        //点进 StringBuilder 类：public final class StringBuilder extends AbstractStringBuilder ，它是一个 final 类，它继承了 AbstractStringBuilder
        //进去：AbstractStringBuilder，能看到：abstract class AbstractStringBuilder implements Appendable, CharSequence {
        //追到这个接口 Appendable，他就是一个抽象的建造者接口。
        //并且 AbstractStringBuilder 这个抽象类里面，很多方法并不抽象而是已经实现了，比如：append(String str) 等，因此就算它用到的设计模式是建造者模式，但也不是完全的照搬原本设计模式的类图来做得完全一致
        //设计者在做设计的时候，可能并不需要完全记住设计模式，这是一种思想。所以，下面仅分析它所对应的建造者模式的角色：
        //1. Appendable接口：定义了 append 方法（都是抽象的），因此它是一个抽象的建造者。
        //2. AbstractStringBuilder抽象类：它虽然是抽象类，但它实现了 Appendable接口的方法，因此它是一个建造者，只是不能实例化。
        //3. StringBuilder ： 它既充当了指挥者角色，同时也充当了具体的建造者（因为可以看到 StringBuilder 重写了 父类（AbstractStringBuilder）的append，但也只是调用父类的 append）。
        StringBuilder sb = new StringBuilder("hello Builder");
        System.out.println(sb);
    }
}
