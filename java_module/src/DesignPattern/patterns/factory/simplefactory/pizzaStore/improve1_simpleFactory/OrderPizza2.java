package DesignPattern.patterns.factory.simplefactory.pizzaStore.improve1_simpleFactory;

import DesignPattern.patterns.factory.simplefactory.pizzaStore.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @date 2022/10/21
 * 
 * 改为静态工厂类时的用法
 */
public class OrderPizza2 {
    //定义一个简单工厂对象
    private Pizza pizza = null;
    private String orderType = "";  //用户输入的类型
    
    //构造器
    public OrderPizza2(){
        do{
            orderType = getType();
            pizza = SimpleFactory.createPizza2(orderType);
            if(pizza == null){
                System.out.println("不存在的种类");
                break;
            }
            //输出 Pizza
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
