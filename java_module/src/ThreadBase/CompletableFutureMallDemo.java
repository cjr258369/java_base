package ThreadBase;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author chenjunran
 * @date 2022/6/20
 */
public class CompletableFutureMallDemo {
    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("dangdang"),
            new NetMall("taobao"),
            new NetMall("PDD")
    );

    /**
     * 按部就班的查询价格，一家家的查
     * @param list
     * @param productName
     * @return
     */
    public static List<String> getPrice(List<NetMall> list, String productName){
        //《mysql书本》in jd price is 88.5
        //先把 list 转成 stream 流，
        //然后 用 map 把list 的每一项做一个映射，同时每一项都有格式，所以用了 String.format() 通过占位符，来格式化每一项，
        //String.format()  接收两个参数：1. String，2. 动态的可变参数，可变参数个数，就对应着占位符。
        //占位符：%s 代表字符串，%.2f代表小数点后保留两位。
        //最后，再把每一项通过 collect 集合为 list
        return list
                .stream()
                .map(netMall ->
                        String.format(productName + " in %s price is %.2f",
                                netMall.getNetMallName(),
                                netMall.calcPrice(productName)))
                .collect(Collectors.toList());
    }

    public static List<String> getPriceByCompletableFuture(List<NetMall> list, String productName){
        //《mysql书本》in jd price is 88.5
        //第一轮的 CompletableFuture ，只返回了：List<CompletableFuture<String>>
        //所以需要再 stream 一下，把 List<CompletableFuture<String>> 转为 List<String> 返回，用了 string 的 join 方法
        return list.stream().map(netMall ->
                CompletableFuture.supplyAsync(() ->
                        String.format(productName + " in %s price is %.2f",
                                netMall.getNetMallName(),
                                netMall.calcPrice(productName))
        )).collect(Collectors.toList())
                .stream()
                .map(s -> s.join())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        //方法一：按部就班
        long startTime = System.currentTimeMillis();
        List<String> mysql = getPrice(list, "mysql");
        for (String s : mysql) {
            System.out.println(s);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("方法一：---costTime：" + (endTime - startTime) + " ms");

        System.out.println("---------------------------------------");

        //方法二：手动实现 CompletableFuture，并依旧使用 流式编程
        long startTime2 = System.currentTimeMillis();
        List<String> mysql2 = getPriceByCompletableFuture(list, "mysql");
        for (String s : mysql2) {
            System.out.println(s);
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("方法二：---costTime：" + (endTime2 - startTime2) + " ms");

    }
}

// 模拟电商网站
class NetMall{
    //电商网站名
    private String netMallName;
    public NetMall(String netMallName){
        this.netMallName = netMallName;
    }

    //查询产品的价格，依据产品名字返回
    public double calcPrice(String productName){
        //暂停几秒钟线程
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        //模拟书名的价格，用随机 double 数 和 书名的首字母相加。
        return  ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);

    }

    public String getNetMallName() {
        return netMallName;
    }

    public void setNetMallName(String netMallName) {
        this.netMallName = netMallName;
    }
}
