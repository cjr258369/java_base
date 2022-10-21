package DesignPattern.patterns.factory.simplefactory.pizzaStore.improve1_simpleFactory;

import DesignPattern.patterns.factory.simplefactory.pizzaStore.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @date 2022/10/21
 */
public class OrderPizza {
    //定义一个简单工厂对象
    private SimpleFactory simpleFactory;
    private Pizza pizza = null;
    
    public void setSimpleFactory(SimpleFactory simpleFactory){
        String orderType = "";   //用户输入的类型
        this.simpleFactory = simpleFactory;
        do{
            orderType = getType();
            pizza = simpleFactory.createPizza(orderType);
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
    
    //构造器
    public OrderPizza(SimpleFactory simpleFactory){
        setSimpleFactory(simpleFactory);
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
