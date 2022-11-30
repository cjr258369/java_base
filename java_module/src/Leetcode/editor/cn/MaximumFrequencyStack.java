package Leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Stack;

/**
 * 2022-11-30 23:37:04
 * [895] - 最大频率栈
 * MaximumFrequencyStack.md
 */
public class MaximumFrequencyStack{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        FreqStack solution = new MaximumFrequencyStack().new FreqStack();
        solution.push(5);
        solution.push(7);
        solution.push(5);
        solution.push(7);
        solution.push(4);
        solution.push(5);
        System.out.println("预期结果：5 , 运行结果：" + solution.pop());
        System.out.println("预期结果：7 , 运行结果：" + solution.pop());
        System.out.println("预期结果：5 , 运行结果：" + solution.pop());
        System.out.println("预期结果：4 , 运行结果：" + solution.pop());
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class FreqStack {
        //存储每个value的频次
        HashMap<Integer,Integer> freq;
        //存储每个频次，都有什么value
        HashMap<Integer, Deque<Integer>> group;
        //当前计算到的最大频次，如果为空的，就减1，往下取
        int maxFreq;
    
        public FreqStack() {
            freq = new HashMap<>();
            group = new HashMap<Integer, Deque<Integer>>();
            maxFreq = 0;
        }
        
        public void push(int val) {
            freq.put(val, freq.getOrDefault(val, 0) + 1);
            group.putIfAbsent(freq.get(val), new ArrayDeque<Integer>());
            group.get(freq.get(val)).push(val);
            maxFreq = Math.max(maxFreq, freq.get(val));
        }
        
        public int pop() {
            int val = group.get(maxFreq).peek();
            freq.put(val, freq.get(val) - 1);
            group.get(maxFreq).pop();
            if(group.get(maxFreq).isEmpty()){
                maxFreq--;
            }
            return val;
        }
    }

    /**
     * Your FreqStack object will be instantiated and called as such:
     * FreqStack obj = new FreqStack();
     * obj.push(val);
     * int param_2 = obj.pop();
     */
    //leetcode submit region end(Prohibit modification and deletion)

}