package Leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

/**
 * 2023-01-18 09:27:21
 * [1825] - 求出 MK 平均值
 * FindingMkAverage.md
 */
 
//给你两个整数 m 和 k ，以及数据流形式的若干整数。你需要实现一个数据结构，计算这个数据流的 MK 平均值 。 
// MK 平均值 按照如下步骤计算： 
// 如果数据流中的整数少于 m 个，MK 平均值 为 -1 ，否则将数据流中最后 m 个元素拷贝到一个独立的容器中。 
// 从这个容器中删除最小的 k 个数和最大的 k 个数。 
// 计算剩余元素的平均值，并 向下取整到最近的整数 。 
//
// 请你实现 MKAverage 类： 
// MKAverage(int m, int k) 用一个空的数据流和两个整数 m 和 k 初始化 MKAverage 对象。 
// void addElement(int num) 往数据流中插入一个新的元素 num 。 
// int calculateMKAverage() 对当前的数据流计算并返回 MK 平均数 ，结果需 向下取整到最近的整数 。 
//
// 示例 1： 
//输入：
//["MKAverage", "addElement", "addElement", "calculateMKAverage", "addElement", 
//"calculateMKAverage", "addElement", "addElement", "addElement", 
//"calculateMKAverage"]
//[[3, 1], [3], [1], [], [10], [], [5], [5], [5], []]
//输出：
//[null, null, null, -1, null, 3, null, null, null, 5]
//
//解释：
//MKAverage obj = new MKAverage(3, 1); 
//obj.addElement(3);        // 当前元素为 [3]
//obj.addElement(1);        // 当前元素为 [3,1]
//obj.calculateMKAverage(); // 返回 -1 ，因为 m = 3 ，但数据流中只有 2 个元素
//obj.addElement(10);       // 当前元素为 [3,1,10]
//obj.calculateMKAverage(); // 最后 3 个元素为 [3,1,10]，删除最小以及最大的 1 个元素后，容器为 [3]，[3] 的平均值等于 3/1 = 3 ，故返回 3
//obj.addElement(5);        // 当前元素为 [3,1,10,5]
//obj.addElement(5);        // 当前元素为 [3,1,10,5,5]
//obj.addElement(5);        // 当前元素为 [3,1,10,5,5,5]
//obj.calculateMKAverage(); // 最后 3 个元素为 [5,5,5]，删除最小以及最大的 1 个元素后，容器为 [5]，[5] 的平均值等于 5/1 = 5 ，故返回 5

// 提示： 
// 3 <= m <= 10⁵ 
// 1 <= k*2 < m 
// 1 <= num <= 10⁵ 
// addElement 与 calculateMKAverage 总操作次数不超过 10⁵ 次。 

public class FindingMkAverage{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        MKAverage solution = new FindingMkAverage().new MKAverage(3, 1);
        solution.addElement(3);
        solution.addElement(1);
        System.out.println("预期结果：-1 , 运行结果：" + solution.calculateMKAverage());
        solution.addElement(10);
        System.out.println("预期结果：3 , 运行结果：" + solution.calculateMKAverage());
        solution.addElement(5);
        solution.addElement(5);
        solution.addElement(5);
        System.out.println("预期结果：5 , 运行结果：" + solution.calculateMKAverage());

        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        
        MKAverage solution2 = new FindingMkAverage().new MKAverage(3, 1);
        solution2.addElement(3716);
        solution2.addElement(51094);
        System.out.println("预期结果：-1 , 运行结果：" + solution2.calculateMKAverage());
        solution2.addElement(56724);
        solution2.addElement(79619);
        System.out.println("预期结果：56724 , 运行结果：" + solution2.calculateMKAverage());
        solution2.addElement(99914);
        solution2.addElement(277);
        System.out.println("预期结果：79619 , 运行结果：" + solution2.calculateMKAverage());
        solution2.addElement(91205);
        //...
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class MKAverage {
        
        private int m, k, size1, size2, size3;
        //存最小 k 个数
        private TreeMap<Integer, Integer> s1;
        //存中间的 m−2k 个元素
        private TreeMap<Integer, Integer> s2;
        //存最大的 k 个元素
        private TreeMap<Integer, Integer> s3;
        //sum2 为 s2 中所有元素之和
        private long sum2;
        //队列 q 保存最后的 m 个元素
        private Deque<Integer> queue;
        
    
        public MKAverage(int m, int k) {
            this.m = m;
            this.k = k;
            this.s1 = new TreeMap<>();
            this.s2 = new TreeMap<>();
            this.s3 = new TreeMap<>();
            this.queue = new ArrayDeque<>();
            this.size1 = this.size2 = this.size3 = 0;
            this.sum2 = 0L;
        }
        
        public void addElement(int num) {
            queue.offer(num);
            if(queue.size() <= m){
                //放入到 s2，并更新 sum2
                s2.put(num, s2.getOrDefault(num, 0) + 1);
                size2++;
                sum2 += num;
                
                //处理 s1 和 s3
                if(queue.size() == m){
                    while(size1 < k){
                        //取出 s2 中的最小值，放入 s1
                        int firstNum = s2.firstKey();
                        s1.put(firstNum, s1.getOrDefault(firstNum, 0) + 1);
                        size1++;
                        sum2 -= firstNum;
                        s2.put(firstNum, s2.get(firstNum) - 1);
                        if(s2.get(firstNum) <= 0){
                            s2.remove(firstNum);
                        }
                        size2--;
                    }

                    while(size3 < k){
                        int lastNum = s2.lastKey();
                        s3.put(lastNum, s3.getOrDefault(lastNum, 0) + 1);
                        size3++;
                        sum2 -= lastNum;
                        s2.put(lastNum, s2.get(lastNum) - 1);
                        if(s2.get(lastNum) <= 0){
                            s2.remove(lastNum);
                        }
                        size2--;
                    }
                }
                return;
            }
            
            //如果 queue 的大小 = m + 1，则先按大小，放入到对应的 s1，s2，s3，再处理要出掉的数字
            if(num < s1.lastKey()){
                s1.put(num, s1.getOrDefault(num, 0) + 1);
                int lastNum = s1.lastKey();
                s2.put(lastNum, s2.getOrDefault(lastNum, 0) + 1);
                size2++;
                sum2 += lastNum;
                s1.put(lastNum, s1.get(lastNum) - 1);
                if(s1.get(lastNum) <= 0){
                    s1.remove(lastNum);
                }
            }else if(num > s3.firstKey()){
                s3.put(num, s3.getOrDefault(num, 0) + 1);
                int firstNum = s3.firstKey();
                s2.put(firstNum, s2.getOrDefault(firstNum, 0) + 1);
                size2++;
                sum2 += firstNum;
                s3.put(firstNum, s3.get(firstNum) - 1);
                if(s3.get(firstNum) <= 0){
                    s3.remove(firstNum);
                }
            }else{
                s2.put(num, s2.getOrDefault(num, 0) + 1);
                size2++;
                sum2 += num;
            }
            //处理要出掉的数字
            int x = queue.poll();
            if(s1.containsKey(x)){
                s1.put(x, s1.get(x) - 1);
                if(s1.get(x) <= 0){
                    s1.remove(x);
                }
                //从 s2 补一个过来
                int firstNum = s2.firstKey();
                s1.put(firstNum, s1.getOrDefault(firstNum, 0 ) + 1);
                sum2 -= firstNum;
                s2.put(firstNum, s2.get(firstNum) - 1);
                if(s2.get(firstNum) <= 0){
                    s2.remove(firstNum);                    
                }
                size2--;
            }else if(s3.containsKey(x)){
                s3.put(x, s3.get(x) - 1);
                if(s3.get(x) <= 0){
                    s3.remove(x);
                }
                //从 s2 补一个过来
                int lastNum = s2.lastKey();
                s3.put(lastNum, s3.getOrDefault(lastNum, 0) + 1);
                sum2 -= lastNum;
                s2.put(lastNum, s2.get(lastNum) - 1);
                if(s2.get(lastNum) <= 0){
                    s2.remove(lastNum);
                }
                size2--;                
            }else{
                s2.put(x, s2.get(x) - 1);
                if(s2.get(x) <= 0){
                    s2.remove(x);
                }
                size2--;
                sum2 -= x;                
            }
        }
        
        public int calculateMKAverage() {
            return queue.size() < m ? -1 : (int)(sum2 / (m - 2 * k));    
        }
    }
    
    /**
     * Your MKAverage object will be instantiated and called as such:
     * MKAverage obj = new MKAverage(m, k);
     * obj.addElement(num);
     * int param_2 = obj.calculateMKAverage();
     */
    //leetcode submit region end(Prohibit modification and deletion)


    //leetcode submit region begin(Prohibit modification and deletion)    
    //简化一些 map 函数的写法    
    class MKAverage2 {
        
        private int m, k, size1, size2, size3;
        //存最小 k 个数
        private TreeMap<Integer, Integer> s1;
        //存中间的 m−2k 个元素
        private TreeMap<Integer, Integer> s2;
        //存最大的 k 个元素
        private TreeMap<Integer, Integer> s3;
        //sum2 为 s2 中所有元素之和
        private long sum2;
        //队列 q 保存最后的 m 个元素
        private Deque<Integer> queue;
        
    
        public MKAverage2(int m, int k) {
            this.m = m;
            this.k = k;
            this.s1 = new TreeMap<>();
            this.s2 = new TreeMap<>();
            this.s3 = new TreeMap<>();
            this.queue = new ArrayDeque<>();
            this.size1 = this.size2 = this.size3 = 0;
            this.sum2 = 0L;
        }
        
        public void addElement(int num) {
            queue.offer(num);
            if(queue.size() <= m){
                //放入到 s2，并更新 sum2
                s2.put(num, s2.getOrDefault(num, 0) + 1);
                size2++;
                sum2 += num;
                
                //处理 s1 和 s3
                if(queue.size() == m){
                    while(size1 < k){
                        //取出 s2 中的最小值，放入 s1
                        int firstNum = s2.firstKey();
                        s1.put(firstNum, s1.getOrDefault(firstNum, 0) + 1);
                        size1++;
                        sum2 -= firstNum;
                        if(s2.put(firstNum, s2.get(firstNum) - 1) <= 0){
                            s2.remove(firstNum);
                        }
                        size2--;
                    }

                    while(size3 < k){
                        int lastNum = s2.lastKey();
                        s3.put(lastNum, s3.getOrDefault(lastNum, 0) + 1);
                        size3++;
                        sum2 -= lastNum;
                        if(s2.put(lastNum, s2.get(lastNum) - 1) <= 0){
                            s2.remove(lastNum);
                        }
                        size2--;
                    }
                }
                return;
            }
            
            //如果 queue 的大小 = m + 1，则先按大小，放入到对应的 s1，s2，s3，再处理要出掉的数字
            if(num < s1.lastKey()){
                s1.put(num, s1.getOrDefault(num, 0) + 1);
                int lastNum = s1.lastKey();
                s2.put(lastNum, s2.getOrDefault(lastNum, 0) + 1);
                size2++;
                sum2 += lastNum;
                if(s1.put(lastNum, s1.get(lastNum) - 1) <= 0){
                    s1.remove(lastNum);
                }
            }else if(num > s3.firstKey()){
                s3.put(num, s3.getOrDefault(num, 0) + 1);
                int firstNum = s3.firstKey();
                s2.put(firstNum, s2.getOrDefault(firstNum, 0) + 1);
                size2++;
                sum2 += firstNum;
                if(s3.put(firstNum, s3.get(firstNum) - 1) <= 0){
                    s3.remove(firstNum);
                }
            }else{
                s2.put(num, s2.getOrDefault(num, 0) + 1);
                size2++;
                sum2 += num;
            }
            //处理要出掉的数字
            int x = queue.poll();
            if(s1.containsKey(x)){
                if(s1.put(x, s1.get(x) - 1) <= 0){
                    s1.remove(x);
                }
                //从 s2 补一个过来
                int firstNum = s2.firstKey();
                s1.put(firstNum, s1.getOrDefault(firstNum, 0 ) + 1);
                sum2 -= firstNum;
                if(s2.put(firstNum, s2.get(firstNum) - 1) <= 0){
                    s2.remove(firstNum);                    
                }
                size2--;
            }else if(s3.containsKey(x)){
                if(s3.put(x, s3.get(x) - 1) <= 0){
                    s3.remove(x);
                }
                //从 s2 补一个过来
                int lastNum = s2.lastKey();
                s3.put(lastNum, s3.getOrDefault(lastNum, 0) + 1);
                sum2 -= lastNum;
                if(s2.put(lastNum, s2.get(lastNum) - 1) <= 0){
                    s2.remove(lastNum);
                }
                size2--;                
            }else{
                if(s2.put(x, s2.get(x) - 1) <= 0){
                    s2.remove(x);
                }
                size2--;
                sum2 -= x;                
            }
        }
        
        public int calculateMKAverage() {
            return queue.size() < m ? -1 : (int)(sum2 / (m - 2 * k));    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}