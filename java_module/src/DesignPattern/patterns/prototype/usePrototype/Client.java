package DesignPattern.patterns.prototype.usePrototype;

/**
 * @date 2022/10/27
 * 
 * 23种设计模式之单例模式：原型模式【使用原型模式完成对象克隆】
 * 
 * 这种方式的克隆，以后 sheep 内部如果新增了属性，或者内容，都会同步的进行克隆，而不像前一种传统的 new 方法那样，
 * 需要改动多处的 Client 端代码，把新的属性给 get 出来，再 set 进去。
 */
public class Client {
    public static void main(String[] args) {
        Sheep sheep = new Sheep("tom", 1, "白色");
        
        Sheep sheep2 = (Sheep) sheep.clone();
        Sheep sheep3 = (Sheep) sheep.clone();
        Sheep sheep4 = (Sheep) sheep.clone();

        System.out.println(sheep2 + " ， hashCode = " + sheep2.hashCode());
        System.out.println(sheep3 + " ， hashCode = " + sheep3.hashCode());
        System.out.println(sheep4 + " ， hashCode = " + sheep4.hashCode());
    }
}
