package ThreadBase.Locks;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author chenjunran
 * @date 2022/6/22
 *
 * 8种锁案例说明
 * 口诀：线程、操作、资源类
 * 只有一部手机：
 * 案例一：标准访问有 ab 两个线程，请问是先打印邮件还是短信？【先邮件 再 短信】
 * 案例二：如果 sendEmail 方法中暂停 3 秒，那么请问是先打印邮件还是短信？【先邮件 再 短信】
 * 案例三：添加一个普通的 hello 方法，请问 b 线程的 hello 会先打印吗？【先hello 再 邮件】
 * 案例四：有两部手机：标准访问有 ab 两个线程，请问是先打印邮件还是短信？【先短信 再 邮件】
 * 案例五：有两个静态的同步方法，只有一个手机，标准访问有 ab 两个线程，请问是先打印邮件还是短信？【先邮件 再 短信】
 * 案例六：有两个静态的同步方法，有两部个手机，标准访问有 ab 两个线程，请问是先打印邮件还是短信？【先邮件 再 短信】
 * 案例七：有一个静态的同步方法 和 一个普通同步方法，只有一部个手机，标准访问有 ab 两个线程，请问是先打印邮件还是短信？【先短信 再 邮件】
 * 案例八：有一个静态的同步方法 和 一个普通同步方法，有两部个手机，标准访问有 ab 两个线程，请问是先打印邮件还是短信？【先短信 再 邮件】
 */
public class Lock8Demo {
    //一切程序的入口
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            phone.sendEmail();
        }, "a").start();

        //暂停毫秒，保证线程1 先启动
        try {TimeUnit.MILLISECONDS.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(() -> {
            //phone.sendSMS();
            //phone.hello();
            phone2.sendSMS();
        }, "b").start();
    }
}

//资源类
class Phone{
    public static synchronized void sendEmail(){
        //暂停毫秒，保证线程1 先启动
        try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("----sendEmail");
    }
    public static synchronized void sendSMS(){
        System.out.println("----sendSMS");
    }

    public void hello(){
        System.out.println("----hello");
    }
}
