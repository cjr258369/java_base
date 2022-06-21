package ThreadBase.CompletableFutureDemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author chenjunran
 * @date 2022/6/21
 */
public class CompletableFutureFastDemo {
    public static void main(String[] args) {
        CompletableFuture<String> playerA = CompletableFuture.supplyAsync(() -> {
            System.out.println("A come in");
            //暂停几秒钟线程
            try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
            return "playerA";
        });

        CompletableFuture<String> playerB = CompletableFuture.supplyAsync(() -> {
            System.out.println("B come in");
            //暂停几秒钟线程
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            return "playerB";
        });

        //线程速度比较
        CompletableFuture<String> winner = playerA.applyToEither(playerB, f -> {
            return f + " is winner";
        });
        System.out.println(Thread.currentThread().getName() + "\t" + winner.join());
    }
}
