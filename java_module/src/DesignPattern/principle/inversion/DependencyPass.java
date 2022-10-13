package DesignPattern.principle.inversion;

/**
 * @date 2022/10/13
 * 
 * 依赖关系传递的三种方式和应用案例
 */
public class DependencyPass {
    public static void main(String[] args) {
        //方式一：
        OpenAndClose1 openAndClose1 = new OpenAndClose1();
        changhong_1 changhong_1 = new changhong_1();
        openAndClose1.open(changhong_1);
        
        //方式二：
        changhong_2 changhong_2 = new changhong_2();
        OpenAndClose2 openAndClose2 = new OpenAndClose2(changhong_2);
        openAndClose2.open();
        
        //方式三：
        changhong_3 changhong_3 = new changhong_3();
        OpenAndClose3 openAndClose3 = new OpenAndClose3();
        openAndClose3.setTv(changhong_3);
        openAndClose3.open();


    }
}

//方式一：通过接口传递实现依赖
//开关接口
interface IOpenAndClose1{
    void open(ITV1 tv);     //抽象方法，接收接口
}
//ITV 接口
interface ITV1{
    void play();    
}
//实现接口
class OpenAndClose1 implements IOpenAndClose1{
    public void open(ITV1 tv) {
        //每种不同的 tv 子类，自己实现不同的 play 方法。
        tv.play();        
    }
}
//电视机子类
class changhong_1 implements ITV1{
    @Override
    public void play() {
        System.out.println("方式一：长虹电视机，打开");
    }
}

//方式二：通过构造方法依赖传递
interface IOpenAndClose2{
    void open();    //抽象方法
}
//ITV 接口
interface ITV2{
    void play();
}
class OpenAndClose2 implements IOpenAndClose2{
    //类内部的这个属性，是一个接口
    public ITV2 tv;

    //通过构造方法传入具体的实现类
    public OpenAndClose2(ITV2 tv) {
        this.tv = tv;
    }
    
    //再去实现 IOpenAndClose2 接口的 open 方法，然后通过 tv 来调用
    public void open(){
        tv.play();
    }
}
//电视机子类
class changhong_2 implements ITV2{
    @Override
    public void play() {
        System.out.println("方式二：长虹电视机，打开");
    }
}

//方式三：通过 setter 方式来传递
interface IOpenAndClose3{
    void open();    //抽象方法
    void setTv(ITV3 tv);
}
//ITV接口
interface ITV3{
    void play();    
}
class OpenAndClose3 implements IOpenAndClose3{
    private ITV3 tv;
    @Override
    public void setTv(ITV3 tv){
        this.tv = tv;
    }
    @Override
    public void open(){
        tv.play();
    }
}

//电视机子类
class changhong_3 implements ITV3{
    @Override
    public void play() {
        System.out.println("方式三：长虹电视机，打开");
    }
}

