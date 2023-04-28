package Leetcode.editor.cn;

import java.util.*;

/**
 * 2023-04-28 10:34:43
 * [1172] - 餐盘栈
 * DinnerPlateStacks.md
 */
 
//我们把无限数量 ∞ 的栈排成一行，按从左到右的次序从 0 开始编号。每个栈的的最大容量 capacity 都相同。
// 实现一个叫「餐盘」的类 DinnerPlates： 
// DinnerPlates(int capacity) - 给出栈的最大容量 capacity。 
// void push(int val) - 将给出的正整数 val 推入 从左往右第一个 没有满的栈。 
// int pop() - 返回 从右往左第一个 非空栈顶部的值，并将其从栈中删除；如果所有的栈都是空的，请返回 -1。 
// int popAtStack(int index) - 返回编号 index 的栈顶部的值，并将其从栈中删除；如果编号 index 的栈是空的，请返回 -1。 
//
// 示例： 
// 输入： 
//["DinnerPlates","push","push","push","push","push","popAtStack","push","push","popAtStack","popAtStack","pop","pop","pop","pop","pop"]
//[[2],[1],[2],[3],[4],[5],[0],[20],[21],[0],[2],[],[],[],[],[]]
//输出：
//[null,null,null,null,null,null,2,null,null,20,21,5,4,3,1,-1]
//
//解释：
//DinnerPlates D = DinnerPlates(2);  // 初始化，栈最大容量 capacity = 2
//D.push(1);
//D.push(2);
//D.push(3);
//D.push(4);
//D.push(5);         // 栈的现状为：    2  4
//                                    1  3  5
//                                    ﹈ ﹈ ﹈
//D.popAtStack(0);   // 返回 2。栈的现状为：     4
//                                          1  3  5
//                                          ﹈ ﹈ ﹈
//D.push(20);        // 栈的现状为：  20  4
//                                   1  3  5
//                                   ﹈ ﹈ ﹈
//D.push(21);        // 栈的现状为：  20  4 21
//                                   1  3  5
//                                   ﹈ ﹈ ﹈
//D.popAtStack(0);   // 返回 20。栈的现状为：       4 21
//                                            1  3  5
//                                            ﹈ ﹈ ﹈
//D.popAtStack(2);   // 返回 21。栈的现状为：      4
//                                            1  3  5
//                                            ﹈ ﹈ ﹈ 
//D.pop()            // 返回 5。栈的现状为：       4
//                                            1  3 
//                                            ﹈ ﹈  
//D.pop()            // 返回 4。栈的现状为：    1  3 
//                                           ﹈ ﹈   
//D.pop()            // 返回 3。栈的现状为：    1 
//                                           ﹈   
//D.pop()            // 返回 1。现在没有栈。
//D.pop()            // 返回 -1。仍然没有栈。
//
// 提示： 
// 1 <= capacity <= 20000 
// 1 <= val <= 20000 
// 0 <= index <= 100000 
// 最多会对 push，pop，和 popAtStack 进行 200000 次调用。 

public class DinnerPlateStacks{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        DinnerPlates solution = new DinnerPlateStacks().new DinnerPlates(2);
        solution.push(1);
        solution.push(2);
        solution.push(3);
        solution.push(4);
        solution.push(5);
        System.out.println("预期结果：2 , 运行结果：" + solution.popAtStack(0));
        solution.push(20);
        solution.push(21);
        System.out.println("预期结果：20 , 运行结果：" + solution.popAtStack(0));
        System.out.println("预期结果：21 , 运行结果：" + solution.popAtStack(2));
        System.out.println("预期结果：5 , 运行结果：" + solution.pop());
        System.out.println("预期结果：4 , 运行结果：" + solution.pop());
        System.out.println("预期结果：3 , 运行结果：" + solution.pop());
        System.out.println("预期结果：1 , 运行结果：" + solution.pop());
        System.out.println("预期结果：-1 , 运行结果：" + solution.pop());
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class DinnerPlates {
        //复杂度分析
        // 时间复杂度：记 n 为 push 的调用次数。单次 push 的时间复杂度为 O(logn)，单次 popAtStack 的时间复杂度为 O(logn)，pop 单次均摊的时间复杂度为 O(logn)。
        // 空间复杂度：使用到的数组和有序集合空间复杂度均为 O(n)。
        
        
        // 栈的容量
        int capacity;
        // 所有栈
        List<Deque<Integer>> list;
        // 最小堆，保存未满栈的下标
        PriorityQueue<Integer> idx;
    
        public DinnerPlates(int capacity) {
            this.capacity = capacity;
            list = new ArrayList<>();
            idx = new PriorityQueue<>();
        }
        
        public void push(int val) {
            if(!idx.isEmpty() && idx.peek() >= list.size()){
                // 堆中都是越界下标，直接清空
                idx.clear();
            }
            if(idx.isEmpty()){
                Deque<Integer> queue = new ArrayDeque<>();
                queue.offerLast(val);
                list.add(queue);
                if(capacity > 1){
                    // 新的栈没有满，下标直接入堆
                    idx.offer(list.size() - 1);
                }
            }else{
                list.get(idx.peek()).offerLast(val);
                if(list.get(idx.peek()).size() >= capacity){
                    idx.poll();
                }
            }
        }
        
        public int pop() {
            // 等价为 popAtStack 最后一个非空栈
            return popAtStack(list.size() - 1);   
        }
        
        public int popAtStack(int index) {
            // 非法操作
            if(index < 0 || index >= list.size() || list.get(index).isEmpty()){
                return -1;
            }
            Deque<Integer> deque = list.get(index);
            if(deque.size() >= capacity){
                // 元素出栈后，栈就不满了，把下标入堆
                idx.offer(index);
            }
            int ans = deque.pollLast();
            // 去掉末尾的空栈（懒删除，堆中下标在 push 时处理）
            while(!list.isEmpty() && list.get(list.size() - 1).isEmpty()){
                list.remove(list.size() - 1);
            }
            return ans;
        }
    }
    
    /**
     * Your DinnerPlates object will be instantiated and called as such:
     * DinnerPlates obj = new DinnerPlates(capacity);
     * obj.push(val);
     * int param_2 = obj.pop();
     * int param_3 = obj.popAtStack(index);
     */
    //leetcode submit region end(Prohibit modification and deletion)

}