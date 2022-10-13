package DesignPattern.principle.inversion;

/**
 * 设计模式七大原则之：依赖倒转【反例】
 * 
 * 分析：
 * 1、好处：简单，比较容易想到并实现
 * 2、缺点：如果获取消息的对象变多，不止email，还有微信，短信等，则这个方案需要新增对应的类（微信信息类，短信信息等），
 *      且Person也要增加相应的接收方法（哪怕是重载也要多写方法）。
 *      
 *  根据依赖倒转原则的解决思路：可以引入一个抽象的接口：IReceiver，表示接受者，这样 Person 类与接口IReceiver发生依赖
 *  因为 Email ，Weixin 等等属于接收的范围，他们各自实现 IReceiver 接口即可
 *  这样才符合依赖倒转原则（高层和低层都依赖抽象，抽象不依赖细节，细节依赖抽象）。
 *  代码：DependencyInversion2.java 
 * @date 2022/10/13
 */
public class DependencyInversion1 {
    public static void main(String[] args) {
        Person1 person = new Person1();
        person.receive(new Email1());
    }
}

class Email1{
    public String getInfo(){
        return "emailInfo: hello";
    }    
}

//完成一个 Person 接收消息的功能
class Person1{
    public void receive(Email1 email){
        System.out.println(email.getInfo());
    }
}
