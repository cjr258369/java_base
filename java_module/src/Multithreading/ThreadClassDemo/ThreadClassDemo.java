package Multithreading.ThreadClassDemo;

public class ThreadClassDemo {

    public static void main(String args[]){
        System.out.println("线程0启动======");
        Thread thread0 = new GuessANumber(27);
        thread0.start();

        try {
            thread0.join();
        } catch (InterruptedException e) {
            System.out.println("线程 thread0 中断");
        }

        System.out.println("线程1启动======");
        Thread thread1 = new GuessANumber(75);
        thread1.start();

        System.out.println("main 方法结束");

    }
}
