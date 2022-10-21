package DesignPattern.patterns.factory.simplefactory.pizzaStore.tradition;

import DesignPattern.patterns.factory.simplefactory.pizzaStore.pizza.CheesePizza;
import DesignPattern.patterns.factory.simplefactory.pizzaStore.pizza.GreekPizza;
import DesignPattern.patterns.factory.simplefactory.pizzaStore.pizza.PepperPizza;
import DesignPattern.patterns.factory.simplefactory.pizzaStore.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @date 2022/10/21
 */
public class OrderPizza {
    //构造器
    public OrderPizza(){
        Pizza pizza = null;
        String orderType;   //订购Pizza 的类型
        do{
            orderType = getType();
            if(orderType.equals("greek")){
                pizza = new GreekPizza();
            }else if(orderType.equals("cheese")){
                pizza = new CheesePizza();
            }else if(orderType.equals("pepper")){
                pizza = new PepperPizza();
            }else {
                break;
            }
            
            pizza.setName(orderType);
            //输出 Pizza 的制作过程
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
