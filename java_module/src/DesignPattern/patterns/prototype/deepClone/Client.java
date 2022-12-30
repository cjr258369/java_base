package DesignPattern.patterns.prototype.deepClone;

/**
 * @date 2022/10/27
 * 
 * 23种设计模式之原型模式：使用原型模式完成对象克隆
 *
 * 深拷贝方式一：重写 clone 方法来实现深拷贝。
 * 深拷贝方式二：通过对象的序列化来实现【推荐】（这里并不使用 json，而是用的 java的原生流，并且能够更好的处理有多引用类型属性的对象）。
 */
public class Client {
    public static void main(String[] args) {
        DeepCloneProtoType p = new DeepCloneProtoType();
        p.setName("测试");
        p.setSheep(new SheepDeepClone1("大佬", 50, "black"));
        
        //方式一：
        DeepCloneProtoType p1 = p.clone();
        //可以看出，引用类型的内容虽然一样，但 hashCode 不一样，是两个实例对象。
        System.out.println("p.name = " + p.getName() + " , p.sheep.hashCode = " + p.getSheep().hashCode() + " , p.sheep = " + p.getSheep());
        System.out.println("p1.name = " + p1.getName() + " , p1.sheep.hashCode = " + p1.getSheep().hashCode() + " , p1.sheep = " + p1.getSheep());

        System.out.println("========================= 分隔符 ===========================");
        
        //方式二：
        DeepCloneProtoType p2 = p.deepClone();
        //可以看出，引用类型的内容虽然一样，但 hashCode 不一样，是两个实例对象。
        System.out.println("p.name = " + p.getName() + " , p.sheep.hashCode = " + p.getSheep().hashCode() + " , p.sheep = " + p.getSheep());
        System.out.println("p2.name = " + p2.getName() + " , p2.sheep.hashCode = " + p2.getSheep().hashCode() + " , p2.sheep = " + p2.getSheep());
    }
}
