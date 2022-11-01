package DesignPattern.patterns.adapter.classAdapter;

/**
 * @date 2022/11/1
 * 
 * 被适配的类
 */
public class Voltage220V {
    
    public int output220V(){
        int src = 220;
        System.out.println("输出电压 = " + src + " 伏");
        return src;
    }
}
