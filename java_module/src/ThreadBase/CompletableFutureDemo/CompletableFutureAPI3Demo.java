package ThreadBase.CompletableFutureDemo;

import java.util.concurrent.CompletableFuture;

/**
 * @author chenjunran
 * @date 2022/6/21
 */
public class CompletableFutureAPI3Demo {
    public static void main(String[] args) {
        //CompletableFuture.supplyAsync(() -> {
        //    return 1;
        //}).thenApply(f -> {
        //    return f + 2;
        //}).thenAccept(f -> {
        //    System.out.println(f);
        //});

        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenRun(() -> {}).join());
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenAccept(f -> System.out.println("消费了："+f)).join());
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenApply(f -> f + " + resultB").join());
    }
}
