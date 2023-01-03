package DesignPattern.patterns.bridge;


/**
 * @date 2023/1/3
 * 直立式类型的手机
 */
public class UpRightPhone extends AbsPhone {
    //构造器
    public UpRightPhone(Brand brand) {
        super(brand);
    }

    public void open(){
        System.out.println("直立式的手机");
        super.open();
    }

    public void close(){
        System.out.println("直立式的手机");
        super.close();
    }

    public void call(){
        System.out.println("直立式的手机");
        super.call();
    }
}
