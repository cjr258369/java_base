package Multithreading.ThreadClassDemo;

/**
 * 通过继承 Thread 类创建线程
 */
public class GuessANumber extends Thread{
    private int number;
    public GuessANumber(int number){
        this.number = number;
    }

    @Override
    public void run(){
        int counter = 0;
        int guess = 0;
        do{
            guess = (int) (Math.random()* 100 + 1);
            System.out.println(this.getName() + " guess = "+guess);
            counter++;
        }while(guess != counter);
        System.out.println("** Correct!" + this.getName() + " in " + counter + " guess.**");
    }
}
