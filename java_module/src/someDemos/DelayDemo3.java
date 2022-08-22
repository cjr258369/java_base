package someDemos;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

// 延迟队列 Demo
public class DelayDemo3 {
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final int CORE_POOL_SIZE = 5; // 最大核心线程
    private static final int MAX_POOL_SIZE = 10; // 最大线程
    private static final long KEEP_ALIVE_TIME = 2L; // 非核心线程保留时长

    public static void main(String[] args) {
        //参考：https://blog.csdn.net/piaoranyuji/article/details/124453884
        //多线程情况下，使用 ConcurrentHashMap，JDK1.7使用分段锁
        DelayDemo3 t = new DelayDemo3();
        t.tes();
    }
    public void tes(){
        //填充数据
        ConcurrentHashMap<String, ArrayDeque<OrderQueue>> map = new ConcurrentHashMap<>();
        OrderQueue order01 = new OrderQueue("111", "Order01");
        OrderQueue order02 = new OrderQueue("222", "Order01");
        OrderQueue order03 = new OrderQueue("333", "Order01");
        OrderQueue order04 = new OrderQueue("444", "Order02");
        OrderQueue order05 = new OrderQueue("555", "Order02");
        OrderQueue order06 = new OrderQueue("666", "Order02");
        OrderQueue order07 = new OrderQueue("777", "Order03");
        OrderQueue order08 = new OrderQueue("888", "Order03");
        OrderQueue order09 = new OrderQueue("999", "Order03");
        OrderQueue order10 = new OrderQueue("101010", "Order04");
        OrderQueue order11 = new OrderQueue("111111", "Order04");
        OrderQueue order12 = new OrderQueue("121212", "Order04");
        OrderQueue order13 = new OrderQueue("131313", "Order05");
        OrderQueue order14 = new OrderQueue("141414", "Order05");
        OrderQueue order15 = new OrderQueue("151515", "Order05");
        map.put("Order01", new ArrayDeque<>());
        map.get("Order01").offer(order01);
        map.get("Order01").offer(order02);
        map.get("Order01").offer(order03);
        map.put("Order02", new ArrayDeque<>());
        map.get("Order02").offer(order04);
        map.get("Order02").offer(order05);
        map.get("Order02").offer(order06);
        map.put("Order03", new ArrayDeque<>());
        map.get("Order03").offer(order07);
        map.get("Order03").offer(order08);
        map.get("Order03").offer(order09);
        map.put("Order04", new ArrayDeque<>());
        map.get("Order04").offer(order10);
        map.get("Order04").offer(order11);
        map.get("Order04").offer(order12);
        map.put("Order05", new ArrayDeque<>());
        map.get("Order05").offer(order13);
        map.get("Order05").offer(order14);
        map.get("Order05").offer(order15);

        System.out.println("所有数据：" + map);

        ConcurrentHashMap<String, Boolean> mapFinish = new ConcurrentHashMap<>();
        for (String key : map.keySet()) {
            mapFinish.put(key, true);
        }
        //任务队列
        DelayQueue<OrderQueue> delayQueue = new DelayQueue<>();
        //生产者线程池
        ExecutorService threadPool1 = Executors.newFixedThreadPool(3);
        //消费者线程池
        ExecutorService threadPool2 = Executors.newFixedThreadPool(5);


        long start = System.currentTimeMillis();
        try {
            //生产完成，且全部消费完毕
            while(!map.isEmpty() || !delayQueue.isEmpty()){
                //遍历所有能生产任务的单号，生成执行任务
                for(Map.Entry<String, Boolean> entry : mapFinish.entrySet()){
                    String key = entry.getKey();
                    if(entry.getValue() && map.containsKey(key)){
                        mapFinish.put(key, false);
                        threadPool1.execute(() -> {
                            OrderQueue a = map.get(key).poll();
                            //该订单执行时间：当前时间 + 10 秒
                            a.setEndTime(System.currentTimeMillis() + 10000);
                            if (delayQueue.offer(a)) {
                                System.out.println((sdf.format(new Date(Long.parseLong(String.valueOf(System.currentTimeMillis())))))
                                        + "\t" + Thread.currentThread().getName()
                                        + " 生产了：" + a.toString());
                            }
                        });
                    }
                    if(map.containsKey(key) && map.get(key).isEmpty()){
                        map.remove(key);
                        mapFinish.put(key, false);
                    }
                }
                System.out.println("before mapFinish = " + mapFinish + " , delayQueue.size() = " + delayQueue.size() + " ， map = " + map);

                //消费所有到期的任务
                while(!delayQueue.isEmpty()){
                    threadPool2.execute(() -> {
                        String key = "";
                        try {
                            OrderQueue queueOrder = delayQueue.poll();
                            if(queueOrder != null){
                                key = queueOrder.getBigOrderId();
                                System.out.println((sdf.format(new Date(Long.parseLong(String.valueOf(System.currentTimeMillis()))))) +"\t" +Thread.currentThread().getName() + " 执行了BPM：" + queueOrder.toString());
                                //调用处理逻辑
                                if(queueOrder.getOrderId() == "131313"){
                                    int a = 10 / 2;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("订单处理异常：" + e.getMessage());
                        }finally{
                            //释放锁
                            if(key != null && map.containsKey(key) && !map.get(key).isEmpty()){
                                mapFinish.put(key, true);
                            }
                        }
                    });
                }
                System.out.println("after mapFinish = " + mapFinish + " , delayQueue.size() = " + delayQueue.size() + " ， map = " + map);
            }
        } finally {
            System.out.println("关闭线程池");
            threadPool1.shutdown();
            threadPool2.shutdown();
        }
        System.out.println("结束了，总耗时：" + (System.currentTimeMillis() - start) + " ms");
    }

    //延时队列的对象【增加多一个 endTime ： 到期时间，按照这个属性判断延时时长】
    //可以直接继承自 保单对象，并实现 delay 方法，因为只需要加多一个 endTime ： 到期时间，按照这个属性判断延时时长
    public class OrderQueue implements Delayed {
        //大单号
        private String bigOrderId;
        //小单号
        private String orderId;
        // 到期时间，按照这个属性判断延时时长。
        private long endTime;

        public OrderQueue(){
        }
        public OrderQueue(String orderId, String bigOrderId){
            this.orderId = orderId;
            this.bigOrderId = bigOrderId;
        }


        // 根据此方法判断延迟任务是否到期。如果返回负数则说明到期，否则未到期
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.endTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        /**
         * 比较时间，将最接近执行时间的任务，排在最前面
         */
        @Override
        public int compareTo(Delayed o) {
            return (int) ((this.endTime - ((OrderQueue)o).endTime));
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public long getEndTime() {
            return endTime;
        }

        // 直接设置到期时间
        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public String getBigOrderId() {
            return bigOrderId;
        }

        public void setBigOrderId(String bigOrderId) {
            this.bigOrderId = bigOrderId;
        }

        @Override
        public String toString() {
            return "OrderQueue{" +
                    "大单号='" + bigOrderId + '\'' +
                    ", 小单号='" + orderId + '\'' +
                    ", 预计执行时间=" + (sdf.format(new Date(Long.parseLong(String.valueOf(endTime))))) +
                    '}';
        }
    }

}
