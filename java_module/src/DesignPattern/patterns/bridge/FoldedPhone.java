package DesignPattern.patterns.bridge;


/**
 * @date 2023/1/3
 * 折叠类型的手机
 */
public class FoldedPhone extends AbsPhone {
    //构造器
    public FoldedPhone(Brand brand) {
        super(brand);
    }

    public void open(){
        System.out.println("折叠式的手机");
        super.open();
    }

    public void close(){
        System.out.println("折叠式的手机");
        super.close();
    }

    public void call(){
        System.out.println("折叠式的手机");
        super.call();
    }
}
