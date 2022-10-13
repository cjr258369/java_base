package DesignPattern.principle.inversion;

/**
 * 设计模式七大原则之：依赖倒转【正例】
 * 
 * @date 2022/10/13
 */
public class DependencyInversion2 {
    public static void main(String[] args) {
        //客户端无需改变，依旧可以传入 Email，传入谁，就调用谁的实现
        Person2 person = new Person2();
        person.receive(new Email2());
        person.receive(new WeiXin());
    }
}

//定义接口
interface IReceiver{
    String getInfo();
}

class Email2 implements IReceiver{
    public String getInfo(){
        return "email info : hello";
    }    
}

//后续，抽象的接口也无需改变，只需增加实现类即可
class WeiXin implements IReceiver{
    public String getInfo() {
        return "weixin Info : hello";
    }
}

//完成一个 Person 接收消息的功能
class Person2{
    public void receive(IReceiver receiver){
        System.out.println(receiver.getInfo());
    }
}
