package Multithreading.CacheThreadPoolDemo;

/**
 * 任务类
 */
public class MyTask implements Runnable {
    private int taskNum;

    public MyTask(int num){
        taskNum = num;
    }

    @Override
    public void run() {
        System.out.println("正在执行Task："+taskNum);
        try{
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task：" + taskNum + " ，执行完毕。");
    }
}
