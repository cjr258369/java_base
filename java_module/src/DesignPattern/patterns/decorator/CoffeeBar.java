package DesignPattern.patterns.decorator;

public class CoffeeBar {
    public static void main(String[] args) {
        //用装饰者模式下一个订单：2份巧克力+1份牛奶的LongBlack
        
        Drink order = new LongBlack();
        System.out.println("订单内容为： " + order.getDesc());
        System.out.println("订单价格： " + order.cost());
        
        //加入一份牛奶
        order = new Milk(order);
        System.out.println("订单内容为： " + order.getDesc());
        System.out.println("订单价格： " + order.cost());

        //加入一份巧克力
        order = new Chocolate(order);
        System.out.println("订单内容为： " + order.getDesc());
        System.out.println("订单价格： " + order.cost());

        //再加入一份巧克力
        order = new Chocolate(order);
        System.out.println("订单内容为： " + order.getDesc());
        System.out.println("订单价格： " + order.cost());
    }
}
