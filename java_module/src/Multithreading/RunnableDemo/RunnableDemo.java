package Multithreading.RunnableDemo;

/**
 * 多线程测试一：
 * 通过实现 Runnable 接口来创建线程
 *
 */
class RunnableDemo implements Runnable {
    private Thread t;
    private String threadName;

    RunnableDemo(String name){
        threadName = name;
        System.out.println("正在创建线程：" + threadName);
    }

    @Override
    public void run() {
        System.out.println("正在执行："+threadName);

        try {
            for(int i=4;i>0;i--){
                System.out.println("当前线程：" + threadName + " ," + i);
                //让线程睡眠一会
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("线程："+ threadName + "中断。");
        }
        System.out.println("线程：" + threadName + " 结束。");
    }

    public void start(){
        System.out.println("启动线程：" + threadName);
        if(t == null){
            t = new Thread(this , threadName);
            t.start();
        }
    }
}
