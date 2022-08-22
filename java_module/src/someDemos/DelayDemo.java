package someDemos;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

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

//package com.avivacofco.dms.bpmInvocation;
//
//        import com.avivacofco.dms.bpmInvocation.service.BpmInvocationManager;
//        import com.avivacofco.dms.bpmInvocation.service.BpmWebserviceInvoker;
//        import com.avivacofco.policy.util.WebSaleSendEmail;
//        import com.avivacofco.quartz.IJob;
//        import com.siebre.bmf.BmfContext;
//        import com.siebre.mapping.CodeMapping;
//        import com.siebre.party.service.PartyManager;
//        import com.siebre.policy.SimpleInsurancePolicy;
//        import com.siebre.policy.service.InsurancePolicyManager;
//        import com.siebre.policy.util.CodeMappingCacheUtils;
//        import com.siebre.util.IPUtil;
//        import org.apache.commons.lang.StringUtils;
//        import org.apache.log4j.Logger;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
//        import org.springframework.util.StopWatch;
//
//        import java.net.UnknownHostException;
//        import java.text.SimpleDateFormat;
//        import java.util.*;
//        import java.util.concurrent.*;
//        import java.util.concurrent.locks.LockSupport;
//
//public class StartUpBpmByMultipleSpecOrderJob implements IJob {
//    public static final Logger log = Logger.getLogger(StartUpBpmByMultipleSpecOrderJob.class);
//
//    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//    @Autowired
//    private BpmInvocationManager bpmInvocationManager;
//
//    @Autowired
//    private InsurancePolicyManager insurancePolicyManager;
//
//    private static String HOST_IP = "";
//    private static StopWatch sw;
//
//    private static final int CORE_POOL_SIZE = 20; // 核心线程数量
//    private static final int MAX_POOL_SIZE = 30; // 最大线程数量
//    private static final long KEEP_ALIVE_TIME = 2L; // 非核心线程保留时长
//    ConcurrentHashMap<String, Boolean> mapState;
//
//
//    public void doJob() {
//        try {
//            log.error("--- start ----");
//            sw = new StopWatch(UUID.randomUUID().toString());
//
//            //if(checkExecuteIp()){
//            //	callBpm(getData());
//            //}
//            checkExecuteIp();
//            //调用BPM
//            callBpm(getData());
//
//            //记录任务耗时耗时
//            log.error(sw.prettyPrint());
//            log.error("所有方法总耗时：" + (sw.getTotalTimeMillis() > 10000 ? sw.getTotalTimeSeconds() + " s" : sw.getTotalTimeMillis() + " ms") + " , 调用方法数量：" + sw.getTaskCount());
//        }catch (Exception e) {
//            e.printStackTrace();
//            log.error(HOST_IP + "StartUpBpmByMultipleSpecOrderJob 的 doJob 执行异常：", e);
//            //sendEMailOnFailure(e.getMessage());
//        }
//    }
//
//    protected static void sendEMailOnFailure(String exception){
//        //设置邮件发送内容
//        try{
//            Map<String,Object> params = new HashMap<String,Object>();
//            StringBuffer content = new StringBuffer();
//            content.append("调用BPM时出错，类StartUpBpmByMultipleSpecOrderJob的doJobSingle/doJob，具体错误信息：").append(exception);
//            params.put("mailTo", BpmReadProperties.read("email"));
//            params.put("content", content.toString());
//            params.put("subject", "调用BPM出错");
//            //WebSaleSendEmail.sendEmail(params);
//        }
//        catch(Exception e){
//            log.error("调用bpm的实时邮件发送失败："+e.getMessage());
//        }
//    }
//
//    //检查本机IP 是否 是执行机器的 IP
//    private boolean checkExecuteIp() throws Exception {
//        sw.start("checkExecuteIp");
//        HOST_IP = IPUtil.getLocalIP();
//        String serverIp ="";
//        List<CodeMapping> codes = CodeMappingCacheUtils.getCodeMappingList("startUpBpmInstanceIP");
//        if(codes != null && codes.size() > 0) {
//            serverIp = codes.get(0).getTargetValue();
//        }
//        sw.stop();
//        return HOST_IP.equals(serverIp);
//    }
//
//    /**
//     * 封装从数据库获取到的数据到 map
//     * @return map = { 5F5FCC37-6D03-29F9-B2D4-7E19FFAD6D94 = {21000991655, 11001785380} , ...}
//     */
//    private ConcurrentHashMap<String, Deque<String>> getData(){
//        sw.start("getData");
//        List<BpmInvocation> bpmInvocationList = bpmInvocationManager.findInvocationByMultipleSpecOrder(BpmInvocationStatus.FAILED);
//        ConcurrentHashMap<String, Deque<String>> map = new ConcurrentHashMap<>();
//
//        //循环按照 MultipleSpecOrderNumber 字段分组
//        //测试取第 100 - 200 条
//        for(int i = 100; i < 150; i++){
//            BpmInvocation bpmInvocation = bpmInvocationList.get(i);
//            String key = bpmInvocation.getMultipleSpecOrderNumber();
//            if(StringUtils.isNotEmpty(bpmInvocation.getPolicyNumber())){
//                if(!map.containsKey(key)){
//                    map.put(key, new LinkedList<String>());
//                }
//                map.get(key).offer(bpmInvocation.getPolicyNumber());
//            }
//        }
//        //for (BpmInvocation bpmInvocation : bpmInvocationList) {
//        //	String key = bpmInvocation.getMultipleSpecOrderNumber();
//        //	if(StringUtils.isNotEmpty(bpmInvocation.getPolicyNumber())){
//        //		if(!map.containsKey(key)){
//        //			map.put(key, new LinkedList<String>());
//        //		}
//        //		map.get(key).offer(bpmInvocation.getPolicyNumber());
//        //	}
//        //}
//        //log.error("本次执行任务的数据：" + map);
//        log.error("map 的大小 = " + map.size());
//        sw.stop();
//        return map;
//    }
//
//    /**
//     * 并发处理起BPM
//     * @param mapPolicy = { 5F5FCC37-6D03-29F9-B2D4-7E19FFAD6D94 = {21000991655, 11001785380} , ...}，调用 getData() 获得的map
//     */
//    private void callBpm(final ConcurrentHashMap<String, Deque<String>> mapPolicy){
//        if(mapPolicy == null || mapPolicy.isEmpty()) return;
//
//        // 取出当前到期任务，执行起 BPM 调用
//        final BpmWebserviceInvoker bpmWebserviceInvoker = (BpmWebserviceInvoker) BmfContext.getBean("bpmWebserviceInvoker");
//
//        //mapState 存 每个主单号当前的状态
//        final ConcurrentHashMap<String, Boolean> mapState = new ConcurrentHashMap<>();
//        for (String s : mapPolicy.keySet()) {
//            mapState.put(s, true);
//        }
//        //延时队列
//        final DelayQueue<SimpleInsurancePolicy> delayQueue = new DelayQueue<>();
//        //生产线程池
//        ThreadPoolExecutor producerThreadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MINUTES,
//                new ArrayBlockingQueue<Runnable>(200), new CustomizableThreadFactory("producer-Thread-Pool"));
//        //消费线程池
//        ThreadPoolExecutor consumerThreadPool = new ThreadPoolExecutor(MAX_POOL_SIZE, CORE_POOL_SIZE + MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MINUTES,
//                new ArrayBlockingQueue<Runnable>(200), new CustomizableThreadFactory("consumer-Thread-Pool"));
//
//        try {
//            //生产没结束 或 消费没结束
//            while(!mapPolicy.isEmpty() || !delayQueue.isEmpty()){
//                //生成 BPM 任务
//                for(Map.Entry<String, Boolean> entry : mapState.entrySet()) {
//                    final String multipleSpecOrderNumber = entry.getKey();
//                    if(entry.getValue() && mapPolicy.containsKey(multipleSpecOrderNumber)){
//                        producerThreadPool.execute(new Runnable() {
//                            @Override
//                            public void run() {
//                                SimpleInsurancePolicy policy = insurancePolicyManager.findInsurancePolicyByPolicyNo(mapPolicy.get(multipleSpecOrderNumber).poll());
//                                if(policy != null){
//                                    //当前 multipleSpecOrderNumber 上锁
//                                    mapState.put(multipleSpecOrderNumber, false);
//                                    policy.setMultipleSpecOrderNumber(multipleSpecOrderNumber);
//                                    //该保单的预期执行时间：当前时间 + 10 秒
//                                    policy.setDelayEndTime(System.currentTimeMillis() + 10000);
//                                    delayQueue.put(policy);
//                                    System.out.println(" -------- delayQueue.size = " + delayQueue.size());
//                                    log.error("生成了，单号：" + multipleSpecOrderNumber + " , 保单号：" + policy.getPolicyNumber());
//                                }
//                            }
//                        });
//                    }
//                    if(mapPolicy.containsKey(multipleSpecOrderNumber) && mapPolicy.get(multipleSpecOrderNumber).isEmpty()){
//                        mapPolicy.remove(multipleSpecOrderNumber);
//                    }
//                }
//
//                System.out.println("delayQueue.size = " + delayQueue.size());
//
//                while(!delayQueue.isEmpty()){
//                    final SimpleInsurancePolicy[] policy = {null};
//                    consumerThreadPool.execute(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                policy[0] = delayQueue.take();
//                                log.error("开始执行 BPM 调用，单号：" + policy[0].getMultipleSpecOrderNumber() + " , 保单号：" + policy[0].getPolicyNumber());
//                                //调用处理逻辑
//                                //bpmWebserviceInvoker.invoke(policy);
//                            } catch (Exception e) {
//                                String msg = policy[0] == null ? "BPM 调用异常，异常信息" : "BPM 调用异常，单号：" + policy[0].getMultipleSpecOrderNumber() + " , 保单号："+ policy[0].getPolicyNumber()+" ， 异常信息：";
//                                log.error(msg, e);
//                            }finally {
//                                if(policy[0] != null){
//                                    // 当前 multipleSpecOrderNumber 的 PolicyNumber 起BPM 结束后， 设置 multipleSpecOrderNumber 的状态为 true，
//                                    // multipleSpecOrderNumber 的 下一个保单任务可以继续生成
//                                    mapState.put(policy[0].getMultipleSpecOrderNumber(), true);
//                                }
//                            }
//                        }
//                    });
//                }
//            }
//        } finally {
//            producerThreadPool.shutdown();
//            consumerThreadPool.shutdown();
//            System.out.println("结束");
//        }
//
//
//    }
//}

