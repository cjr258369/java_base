package ThreadBase.objectThread;


import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class JOLDemo {
    public static void main(String[] args) {
        //Thread.currentThread 这个叫当前线程
        //与线程那个类似，VM.current() 这个叫当前虚拟机
        //System.out.println(VM.current().details());

        //对象的对齐倍数【意思是所有对象分配的字节都是 8 的整数倍】
        //System.out.println(VM.current().objectAlignment());

        //Object obj = new Object();
        //打印 obj 这个Java 对象的内部信息及描述：
        // OFFSET：偏移量，也就是到这个字段为止所占用的 byte 数。
        // SIZE：大小，后面类型的字节大小
        // TYPE：类型，是Class中定义的类型
        // DESCRIPTION：描述
        // VALUE：值
        //System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        Customer1 c1 = new Customer1();
        //如果没有属性，也是默认的 16 个字节。
        System.out.println(ClassLayout.parseInstance(c1).toPrintable());

        //Customer2 c2 = new Customer2();
        //如果没有属性，也是默认的 16 个字节。
        //System.out.println(ClassLayout.parseInstance(c2).toPrintable());

    }
}

class Customer1{
    //只有对象头，没有其他任何实例数据

    //因为压缩指针默认是开启的，所以如果没有实例数据，对象头里面的 类型指针会 从 8位 压缩为 4位，
    // 从而使得这个示例看上去，对象头里面的 kclass 只占了 4 位
    //java -XX:+PrintCommandLineFlags -version  【java 虚拟机启动时打印所有配置好的用到了的全部参数】
    // +： 代表开启
    // Print：打印
    // CommandLineFlags：所有的参数配置
    // 使用减号，关闭压缩指针：-XX:-UseCompressedClassPointers 关闭压缩指针后，再看就是整的 8 + 8 了
    // 其他说明写到笔记...
}
class Customer2{

    //存在实例数据 int + boolean ,默认满足对齐起填充，实例大小为 24 字节
    int id;
    boolean flag = false;
}
