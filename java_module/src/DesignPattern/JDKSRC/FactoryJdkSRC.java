package DesignPattern.JDKSRC;

import java.util.Calendar;

/**
 * @date 2022/10/25
 * 
 * 在 JDK 的 Calendar 中，就是使用了简单工厂模式。
 */
public class FactoryJdkSRC {
    public static void main(String[] args) {
        // getInstance 是 Calendar 的静态方法，里面调用了 createCalendar()，依据时区，和地区来进行返回。
        // createCalendar() 方法的 1672 行开始，创建 cal 里用了switch case，就是简单工厂模式，根据不同地区的类型，创建不同的 cal 返回。
        // debug 代码： Calendar cal = Calendar.getInstance(); 即可看到使用默认的时区来创建 cal 的整个过程，以观察工厂模式的源码应用
        // 注意：idea 默认不进源码，需要到源码里面再打两个断点。
        Calendar cal = Calendar.getInstance();
        //注意 月份下标从 0 开始，所以取月份要 +1
        System.out.println("年：" + cal.get(Calendar.YEAR));
        System.out.println("月：" + (cal.get(Calendar.MONDAY) + 1));
        System.out.println("日：" + cal.get(Calendar.DAY_OF_MONTH));
        System.out.println("时：" + cal.get(Calendar.HOUR_OF_DAY));
        System.out.println("分：" + cal.get(Calendar.MINUTE));
        System.out.println("秒：" + cal.get(Calendar.SECOND));
    }
}
