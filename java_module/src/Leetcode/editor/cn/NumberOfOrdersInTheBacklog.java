package Leetcode.editor.cn;

import java.util.Collections;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * 2023-01-03 10:59:26
 * [1801] - 积压订单中的订单总数
 * NumberOfOrdersInTheBacklog.md
 */
 
//给你一个二维整数数组 orders ，其中每个 orders[i] = [pricei, amounti, orderTypei] 表示有 amounti 
//笔类型为 orderTypei 、价格为 pricei 的订单。 
//
// 订单类型 orderTypei 可以分为两种：
// 0 表示这是一批采购订单 buy 
// 1 表示这是一批销售订单 sell 
//
// 注意，orders[i] 表示一批共计 amounti 笔的独立订单，这些订单的价格和类型相同。对于所有有效的 i ，由 orders[i] 表示的所有订单提交时间均早于 orders[i+1] 表示的所有订单。 
//
// 存在由未执行订单组成的 积压订单 。积压订单最初是空的。提交订单时，会发生以下情况：
// 1. 如果该订单是一笔采购订单 buy ，则可以查看积压订单中价格 最低 的销售订单 sell 。如果该销售订单 sell 的价格 低于或等于 当前采购订单buy 的价格，则匹配并执行这两笔订单，并将销售订单 sell 从积压订单中删除。否则，采购订单 buy 将会添加到积压订单中。 
// 2. 反之亦然，如果该订单是一笔销售订单 sell ，则可以查看积压订单中价格 最高 的采购订单 buy 。如果该采购订单 buy 的价格 高于或等于 当前销售订单 sell 的价格，则匹配并执行这两笔订单，并将采购订单 buy 从积压订单中删除。否则，销售订单 sell 将会添加到积压订单中。 
//
// 输入所有订单后，返回积压订单中的 订单总数 。由于数字可能很大，所以需要返回对 10⁹ + 7 取余的结果。 
//
// 示例 1： 
//输入：orders = [[10,5,0],[15,2,1],[25,1,1],[30,4,0]]
//输出：6
//解释：输入订单后会发生下述情况：
//- 提交 5 笔采购订单，价格为 10 。没有销售订单，所以这 5 笔订单添加到积压订单中。
//- 提交 2 笔销售订单，价格为 15 。没有采购订单的价格大于或等于 15 ，所以这 2 笔订单添加到积压订单中。
//- 提交 1 笔销售订单，价格为 25 。没有采购订单的价格大于或等于 25 ，所以这 1 笔订单添加到积压订单中。
//- 提交 4 笔采购订单，价格为 30 。前 2 笔采购订单与价格最低（价格为 15）的 2 笔销售订单匹配，从积压订单中删除这 2 笔销售订单。第 3 笔
//采购订单与价格最低的 1 笔销售订单匹配，销售订单价格为 25 ，从积压订单中删除这 1 笔销售订单。积压订单中不存在更多销售订单，所以第 4 笔采购订单需要添
//加到积压订单中。
//最终，积压订单中有 5 笔价格为 10 的采购订单，和 1 笔价格为 30 的采购订单。所以积压订单中的订单总数为 6 。
// 
// 示例 2： 
//输入：orders = [[7,1000000000,1],[15,3,0],[5,999999995,0],[5,1,1]]
//输出：999999984
//解释：输入订单后会发生下述情况：
//- 提交 10⁹ 笔销售订单，价格为 7 。没有采购订单，所以这 10⁹ 笔订单添加到积压订单中。
//- 提交 3 笔采购订单，价格为 15 。这些采购订单与价格最低（价格为 7 ）的 3 笔销售订单匹配，从积压订单中删除这 3 笔销售订单。
//- 提交 999999995 笔采购订单，价格为 5 。销售订单的最低价为 7 ，所以这 999999995 笔订单添加到积压订单中。
//- 提交 1 笔销售订单，价格为 5 。这笔销售订单与价格最高（价格为 5 ）的 1 笔采购订单匹配，从积压订单中删除这 1 笔采购订单。
//最终，积压订单中有 (1000000000-3) 笔价格为 7 的销售订单，和 (999999995-1) 笔价格为 5 的采购订单。所以积压订单中的订单总
//数为 1999999991 ，等于 999999984 % (10⁹ + 7) 。 
//
// 提示： 
// 1 <= orders.length <= 10⁵ 
// orders[i].length == 3 
// 1 <= pricei, amounti <= 10⁹ 
// orderTypei 为 0 或 1 

public class NumberOfOrdersInTheBacklog{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new NumberOfOrdersInTheBacklog().new Solution();
        System.out.println("预期结果：6 , 运行结果：" + solution.getNumberOfBacklogOrders(new int[][]{{10,5,0},{15,2,1},{25,1,1},{30,4,0}}));
        System.out.println("预期结果：999999984 , 运行结果：" + solution.getNumberOfBacklogOrders(new int[][]{{7,1000000000,1},{15,3,0},{5,999999995,0},{5,1,1}}));
        
        System.out.println("方案一 耗时：" + (System.currentTimeMillis() - start) + " ms");
        System.out.println("===============方案二================");
        start = System.currentTimeMillis();
        
        Solution2 solution2 = new NumberOfOrdersInTheBacklog().new Solution2();
        System.out.println("预期结果：6 , 运行结果：" + solution2.getNumberOfBacklogOrders(new int[][]{{10,5,0},{15,2,1},{25,1,1},{30,4,0}}));
        System.out.println("预期结果：999999984 , 运行结果：" + solution2.getNumberOfBacklogOrders(new int[][]{{7,1000000000,1},{15,3,0},{5,999999995,0},{5,1,1}}));
        
        System.out.println("方案二 耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    //该版本：【47 ms，75.2 MB】，内存消耗低，但效率慢，存在重复代码，下面方案二会重新设计类，并对关键方法进行抽取，做到了一目了然，并且优化效率。
    //如果方案一：采用与方案二类似的方法，不频繁地进行入队与出队（即判断等于0才出队，否则只查看一下堆顶），则可优化至：【36 ms，75.5 MB】，但方案二的可读性非常好。
    class Solution {
        public int getNumberOfBacklogOrders(int[][] orders) {
            //buy（大到小）， sell（小到大）
            PriorityQueue<int[]> buyOrders = new PriorityQueue<>((a, b) -> b[0] - a[0]);
            PriorityQueue<int[]> sellOrders = new PriorityQueue<>((a, b) -> a[0] - b[0]);
            
            for(int[] order : orders){
                if(order[2] == 0){
                    //当前为采购单
                    while(order[1] > 0 && !sellOrders.isEmpty() && sellOrders.peek()[0] <= order[0]){
                        int[] sellOrder = sellOrders.poll();
                        int sellAmount = Math.min(sellOrder[1], order[1]);
                        sellOrder[1] -= sellAmount;
                        order[1] -= sellAmount;
                        if(sellOrder[1] > 0){
                            sellOrders.offer(sellOrder);
                        }
                    }
                    if(order[1] > 0){
                        buyOrders.offer(order);
                    }
                }else{
                    while(order[1] > 0 && !buyOrders.isEmpty() && buyOrders.peek()[0] >= order[0]){
                        int[] buyOrder = buyOrders.poll();
                        int buyAmount = Math.min(buyOrder[1], order[1]);
                        buyOrder[1] -= buyAmount;
                        order[1] -= buyAmount; 
                        if(buyOrder[1] > 0){
                            buyOrders.offer(buyOrder);
                        }
                    }
                    if(order[1] > 0){
                        sellOrders.offer(order);
                    }
                }
            }
            
            long ans = 0L;
            while(!buyOrders.isEmpty() || !sellOrders.isEmpty()){
                ans = (ans + (buyOrders.isEmpty() ? 0 : buyOrders.poll()[1])) % 1000000007;
                ans = (ans + (sellOrders.isEmpty() ? 0 : sellOrders.poll()[1])) % 1000000007;
            }
            return (int)ans;
        }
    }
    
    //方案二：耗时低，但内存消耗稍高，因为所有 orders 都会处理为一个对象，再放入优先队列。【23 ms，85.3 MB】
    class Solution2 {
        int MOD = 1000000007;
        PriorityQueue<Order> buyQ;
        PriorityQueue<Order> sellQ;
        public int getNumberOfBacklogOrders(int[][] orders) {
            //<价格, 数目>，大顶堆
            buyQ = new PriorityQueue<>(10, Collections.reverseOrder());
            //<价格, 数目>，小顶堆
            sellQ = new PriorityQueue<>();
            
            for(int[] rawOrder : orders){
                Order order = new Order(rawOrder);
                if(order.isBuyOrder()){
                    checkBuy(order);
                }else{
                    checkSell(order);
                }
            }
            
            int orderCount = (getOrderCount(buyQ) + getOrderCount(sellQ)) % MOD;
            return orderCount;
        }
        
        private void checkBuy(Order order){
            while(order.amount > 0 && !sellQ.isEmpty() && order.price >= sellQ.peek().price){
                Order sellOrder = sellQ.peek();
                int sellAmount = Math.min(sellOrder.amount, order.amount);
                order.amount -= sellAmount;
                sellOrder.amount -= sellAmount;
                if(sellOrder.amount == 0){
                    sellQ.poll();
                }
            }
            if(order.amount > 0){
                buyQ.offer(order);
            }
        }
        
        private void checkSell(Order order){
            while(order.amount > 0 && !buyQ.isEmpty() && order.price <= buyQ.peek().price){
                Order buyOrder = buyQ.peek();
                int buyAmount = Math.min(buyOrder.amount, order.amount);
                buyOrder.amount -= buyAmount;
                order.amount -= buyAmount;
                if(buyOrder.amount == 0){
                    buyQ.poll();
                }
            }
            if(order.amount > 0){
                sellQ.offer(order);
            }
            
        }
        
        private int getOrderCount(PriorityQueue<Order> orderPQ){
            int orderCount = 0;
            for(Order order : orderPQ){
                orderCount += order.amount;
                orderCount %= MOD;
            }
            return orderCount;            
        }
        
        class Order implements Comparable<Order>{
            //final int price;
            //final int orderType;
            int price;
            int orderType;
            int amount;
            
            public Order(int[] rawOrder){
                this.price = rawOrder[0];
                this.amount = rawOrder[1];
                this.orderType = rawOrder[2];
            }

            @Override
            public int compareTo(Order o) {
                return this.price - o.price;
            }
            
            @Override
            public boolean equals(Object o){
                if(this == o){
                    return true;
                }
                if(o == null || getClass() != o.getClass()){
                    return false;
                }
                Order order = (Order)o;
                return this.orderType == order.orderType && this.price == order.price && this.amount == order.amount;
            }
            
            @Override
            public int hashCode(){
                return Objects.hash(orderType, price, amount);
            }
            
            public boolean isBuyOrder(){
                return this.orderType == 0;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}