package ThreadBase.interrupt;

/**
 * @author chenjunran
 * @date 2022/7/6
 */
public class interruptDemo3 {
    public static void main(String[] args) {
        // 测试当前线程是否被中断（检查中断标识），返回一个 Boolean 并清除中断状态
        // 第二次再调用时，中断状态已经被清除，将返回一个 false
        //直接使用 main 线程示例 ， Thread.interrupted() 这个静态方法。
        System.out.println(Thread.currentThread().getName() + "\t " + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "\t " + Thread.interrupted());
        System.out.println("-----1");
        //将中断标识设置为 true
        Thread.currentThread().interrupt();
        System.out.println("-----2");
        System.out.println(Thread.currentThread().getName() + "\t " + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "\t " + Thread.interrupted());

        //Thread.interrupted();   //静态方法
        //Thread.currentThread().isInterrupted();     //实例方法
    }
}
