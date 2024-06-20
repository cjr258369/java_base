package DesignPattern.JDKSRC;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DecoratorJdkSRC {
    public static void main(String[] args) throws IOException {
        //说明
        //1. InputStream 是抽象类，类似前面写的 Drink
        //2. FileInputStream 是 InputStream 的子类，类似前面写的 LongBlack、ShortBlack...
        //3. FilterInputStream 是 InputStream 的子类，类似前面写的 Decorator 修饰者
        //4. DataInputStream 是 FilterInputStream 的子类，具体的修饰者，类似前面写的 Milk，Soy 等
        //5. FilterInputStream 类中，有 protected volatile InputStream in; 即含被修饰者
        //6. 分析得出在 jdk 的 io 体系中，就是使用装饰者模式
        
        //这一段，就类似那个单品咖啡：new FileInputStream("D:\123.txt")
        DataInputStream dis = new DataInputStream(new FileInputStream("D:\123.txt"));
        System.out.println(dis.read());
        dis.close();
    }
}
