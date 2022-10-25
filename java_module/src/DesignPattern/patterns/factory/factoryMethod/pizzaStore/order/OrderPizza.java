package DesignPattern.patterns.factory.factoryMethod.pizzaStore.order;

import DesignPattern.patterns.factory.factoryMethod.pizzaStore.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @date 2022/10/21
 */
public abstract class OrderPizza {
    //定义一个抽象方法：createPizza，让各个子类自己实现
    abstract Pizza createPizza(String orderType);

    //构造器
    public OrderPizza(){
        Pizza pizza = null;
        String orderType = "";   //用户输入的类型
        do{
            orderType = getType();
            pizza = createPizza(orderType); //抽象方法，由工厂子类实现
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
