package DesignPattern.patterns.factory.absFactory.pizzaStore.order;

import DesignPattern.patterns.factory.absFactory.pizzaStore.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @date 2022/10/25
 */
public class OrderPizza1 {
    AbsFactory factory;
    
    //构造方法
    public OrderPizza1(AbsFactory factory){
        setFactory(factory);
    }
    
    private void setFactory(AbsFactory factory){
        Pizza pizza = null;
        String orderType = "";  //用户输入
        this.factory = factory;
        
        do{
            orderType = getType();
            //factory 可能是北京的工厂子类，也可能是伦敦的工厂子类
            pizza = factory.createPizza(orderType);
            if(pizza == null){
                System.out.println("不存在的种类");
                break;
            }
            //订购成功
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();            
        }while(true);        
        
    }

    //获取用户希望订购的披萨种类
    private String getType(){
        try{
            BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input pizza 种类:");
            return strin.readLine();
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }
    }
}
