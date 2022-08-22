package someDemos;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

// 延迟队列 Demo
public class DelayDemo2 {
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final int CORE_POOL_SIZE = 5; // 最大核心线程
    private static final int MAX_POOL_SIZE = 10; // 最大线程
    private static final long KEEP_ALIVE_TIME = 2L; // 非核心线程保留时长

    public static void main(String[] args) {
        //参考：https://blog.csdn.net/piaoranyuji/article/details/124453884
        //多线程情况下，使用 ConcurrentHashMap，JDK1.7使用分段锁
        DelayDemo2 t = new DelayDemo2();
        t.tes();
    }
    public void tes(){
        //填充数据
        HashMap<String, ArrayDeque<OrderQueue>> map = new HashMap<>();
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


        final DelayOrderResource resource = new DelayOrderResource(map);

        //生产者线程池
        ExecutorService threadPool1 = Executors.newFixedThreadPool(3);
        try {
            threadPool1.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("生产者线程启动");
                    while (!resource.purduceFinished && !resource.mapOrder.isEmpty()) {
                        try {
                            resource.produce();
                        } catch (Exception e) {
                            System.out.println("延迟队列生产异常" + e.getMessage());
                        }
                    }
                    System.out.println("所有保单，生成任务完毕，生产结束了");
                }
            });
        } finally {
            threadPool1.shutdown();
        }

        //消费者线程池
        ExecutorService threadPool2 = Executors.newFixedThreadPool(3);
        try {
            threadPool2.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("消费者线程启动");
                    while (!resource.purduceFinished || !resource.delayQueue.isEmpty()) {
                        try {
                            resource.consume();
                        } catch (Exception e) {
                            System.out.println("CallBPM执行异常" + e.getMessage());
                        }
                    }
                    System.out.println("所有任务起BPM完成，消费者结束。");
                }
            });
        } finally {
            threadPool2.shutdown();
        }
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
    //延时队列
    public class DelayOrderResource{
        private DelayQueue<OrderQueue> delayQueue = new DelayQueue<>();
        HashMap<String, ArrayDeque<OrderQueue>> mapOrder;
        volatile HashMap<String, Boolean> mapFinish = new HashMap<>();
        //生产者生产完所有保单
        volatile boolean purduceFinished = false;

        public DelayOrderResource(){

        }

        public DelayOrderResource(HashMap<String, ArrayDeque<OrderQueue>> mapOrder){
            this.mapOrder = mapOrder;
            for (String s : mapOrder.keySet()) {
                mapFinish.put(s, true);
            }
        }


        /**
         * 生产者向延迟队列中添加资源
         */
        public void produce() {
            try {

                while(!(purduceFinished = mapOrder.isEmpty())){
                    Iterator<Map.Entry<String, ArrayDeque<OrderQueue>>> entryIterator = mapOrder.entrySet().iterator();
                    while (entryIterator.hasNext()) {
                        Map.Entry<String, ArrayDeque<OrderQueue>> entry = entryIterator.next();
                        String bigOrderNum = entry.getKey();
                        //判断当前是否可以生产任务了
                        if(mapFinish.get(bigOrderNum)){
                            OrderQueue a = entry.getValue().poll();
                            //该订单执行时间：当前时间 + 10 秒
                            a.setEndTime(System.currentTimeMillis() + 10000);
                            mapFinish.put(bigOrderNum, false);
                            delayQueue.put(a);
                            System.out.println((sdf.format(new Date(Long.parseLong(String.valueOf(System.currentTimeMillis()))))) + Thread.currentThread().getName() + " 生产了：" + a.toString());
                        }
                        if(entry.getValue().isEmpty()){
                            entryIterator.remove();
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println("订单生产出现异常" + e.getMessage());
            }
        }

        /**
         * 将队列中的到期的订单起BPM
         */
        public void consume() {
            try {
                OrderQueue queueOrder = delayQueue.take();
                System.out.println((sdf.format(new Date(Long.parseLong(String.valueOf(System.currentTimeMillis()))))) + Thread.currentThread().getName() + " 执行了BPM：" + queueOrder.toString());
                //调用处理逻辑
                mapFinish.put(queueOrder.getBigOrderId(), true);
            } catch (Exception e) {
                System.out.println("订单处理异常：" + e.getMessage());
            }
        }
    }

}
