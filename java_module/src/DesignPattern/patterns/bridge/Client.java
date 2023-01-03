package DesignPattern.patterns.bridge;

/**
 * @date 2023/1/3
 */
public class Client {
    public static void main(String[] args) {
        //获取折叠式的手机（样式 + 品牌）
        
        //折叠的小米手机
        AbsPhone phone1 = new FoldedPhone(new XiaoMi());
        phone1.open();
        phone1.call();
        phone1.close();
        System.out.println("==========================");
        //折叠的vivo手机
        AbsPhone phone2 = new FoldedPhone(new Vivo());
        phone2.open();
        phone2.call();
        phone2.close();


        System.out.println("============增加样式，只需要增加一个类，所有品牌就全都增加了==============");
        AbsPhone phone3 = new UpRightPhone(new XiaoMi());
        phone3.open();
        phone3.call();
        phone3.close();
        System.out.println("==========================");
        AbsPhone phone4 = new UpRightPhone(new Vivo());
        phone4.open();
        phone4.call();
        phone4.close();
    }
}
