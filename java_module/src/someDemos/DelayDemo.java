package someDemos;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

// 延迟队列 Demo
public class DelayDemo {
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        //参考：https://blog.csdn.net/piaoranyuji/article/details/124453884
        //多线程情况下，使用 ConcurrentHashMap，JDK1.7使用分段锁
        DelayDemo t = new DelayDemo();
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
        map.put("Order01", new ArrayDeque<>());
        map.get("Order01").offer(order01);
        map.get("Order01").offer(order02);
        map.get("Order01").offer(order03);
        map.put("Order02", new ArrayDeque<>());
        map.get("Order02").offer(order04);
        map.get("Order02").offer(order05);
        map.get("Order02").offer(order06);

        System.out.println("所有数据：" + map);


        DelayOrderResource resource = new DelayOrderResource(map);
        // 创建一个生产者线程
        ProducerDelayQueueThread p = new ProducerDelayQueueThread(resource);
        // 目前只开启一个生产者线程和一个消费者线程，后续可以改成线程池
        ConsumerDelayQueueThread c = new ConsumerDelayQueueThread(resource);
        // start 生产者和消费者线程，开始工作
        p.start();
        c.start();
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
                            System.out.println((sdf.format(new Date(Long.parseLong(String.valueOf(System.currentTimeMillis()))))) + " 生产了：" + a.toString());
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
         * 将延迟队列中的订单移除并处理
         */
        public void consume() {
            try {
                OrderQueue queueOrder = delayQueue.take();
                System.out.println((sdf.format(new Date(Long.parseLong(String.valueOf(System.currentTimeMillis()))))) + " 执行了BPM：" + queueOrder.toString());
                //调用处理逻辑
                mapFinish.put(queueOrder.getBigOrderId(), true);
            } catch (Exception e) {
                System.out.println("订单处理异常：" + e.getMessage());
            }
        }
    }

    //生产者线程池
    public class ProducerDelayQueueThread extends Thread{
        private DelayOrderResource resource;

        public ProducerDelayQueueThread(DelayOrderResource resource) {
            this.resource = resource;
        }

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
    }

    //消费者线程池
    public class ConsumerDelayQueueThread extends Thread {
        private DelayOrderResource resource;
        public ConsumerDelayQueueThread(DelayOrderResource resource) {
            this.resource = resource;
        }

        public void run() {
            System.out.println("消费者线程启动");
            while (!resource.purduceFinished || !resource.delayQueue.isEmpty()) {
                try {
                    resource.consume();
                } catch (Exception e) {
                    System.out.println("延迟队列消费异常" + e.getMessage());
                }
            }
            System.out.println("所有任务起BPM完成，消费者结束");
        }

    }

}
