package DesignPattern.JDKSRC;

public class FlyWeightJdkSRC {
    public static void main(String[] args) {
        //如果 Integer.valueOf(x) 的 x 在 -128~127 的范围内，就是使用享元模式返回，如果不在范围内，则仍然 new
        //小结：
        //1. 在 valueOf 方法中，先判断值是否在 IntegerCache 中，如果不在，就创建 Integer(new)，否则，就直接从缓存池返回
        //2. valueOf 方法就用到了享元模式
        //3. 如果使用 valueOf 方法得到一个 Integer 实例，范围在 -128~127，执行速度比 new 快
        Integer x = Integer.valueOf(127);
        Integer y = new Integer(127);
        Integer z = Integer.valueOf(127);
        Integer w = new Integer(127);

        System.out.println(x.equals(y));    //大小：true
        System.out.println(x == y);     //不是同一个对象 false
        System.out.println(x == z);     //valueof 返回了同一个对象，所以 true
        System.out.println(w == x);     //false
        System.out.println(w == y);     //false
        
        //超范围，直接new，所以下面会返回 false
        Integer x1 = Integer.valueOf(200);
        Integer x2 = Integer.valueOf(200);
        System.out.println("x1 == x2 的结果：" + (x1 == x2));
    }
}
