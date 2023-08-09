package Leetcode.editor.cn;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 2023-06-07 09:21:37
 * [2611] - 老鼠和奶酪
 * MiceAndCheese.md
 */
 
//有两只老鼠和 n 块不同类型的奶酪，每块奶酪都只能被其中一只老鼠吃掉。
// 下标为 i 处的奶酪被吃掉的得分为： 
// 如果第一只老鼠吃掉，则得分为 reward1[i] 。 
// 如果第二只老鼠吃掉，则得分为 reward2[i] 。 
//
// 给你一个正整数数组 reward1 ，一个正整数数组 reward2 ，和一个非负整数 k 。 
// 请你返回第一只老鼠恰好吃掉 k 块奶酪的情况下，最大 得分为多少。 
//
// 示例 1： 
//输入：reward1 = [1,1,3,4], reward2 = [4,4,1,1], k = 2
//输出：15
//解释：这个例子中，第一只老鼠吃掉第 2 和 3 块奶酪（下标从 0 开始），第二只老鼠吃掉第 0 和 1 块奶酪。
//总得分为 4 + 4 + 3 + 4 = 15 。15 是最高得分。
//
// 示例 2： 
//输入：reward1 = [1,1], reward2 = [1,1], k = 2
//输出：2
//解释：这个例子中，第一只老鼠吃掉第 0 和 1 块奶酪（下标从 0 开始），第二只老鼠不吃任何奶酪。
//总得分为 1 + 1 = 2 。2 是最高得分。
//
// 提示： 
// 1 <= n == reward1.length == reward2.length <= 10⁵ 
// 1 <= reward1[i], reward2[i] <= 1000 
// 0 <= k <= n 

public class MiceAndCheese{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MiceAndCheese().new Solution();
        System.out.println("预期结果：15 , 运行结果：" + solution.miceAndCheese2(new int[]{1, 1, 3, 4}, new int[]{4, 4, 1, 1}, 2));
        System.out.println("预期结果：2 , 运行结果：" + solution.miceAndCheese2(new int[]{1, 1}, new int[]{1, 1}, 2));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //这道题是要从两个数组中共同选出n个位置的数，其中 数组1 选出 k 个数，数组2 选出 n - k 个数，使得这些元素的和最大。
        // 如果直接从选数的思路去思考，如何在两个数组中选出合适的元素并不容易。
        // 我们可以换个角度，假设一开始 n 个元素全由 数组2 提供，记和为maxScore。现在我们的目标就是从 数组1 中找到 k 个元素替换 数组2 相同位置的元素，使得maxScore最大。
        // 假设从 数组1 选择位置j 的元素替换 数组2 的元素，那么maxScore = maxScore + (reward1[j] - reward2[j])。因此找出k个元素进行替换的结果为：maxScore = maxScore + k个求和(reward1[j] - reward2[j])
        
        //贪心 + 排序复杂度分析
        // 时间复杂度：O(nlogn)，其中 n 是数组 reward1  和 reward2  的长度。创建数组 diffs 需要 O(n) 的时间，将数组 diffs 排序需要 O(nlogn) 的时间，排序后计算 diffs 的 k 个最大值之和需要 O(k) 的时间，其中 k≤n，因此时间复杂度是 O(nlogn)。
        // 空间复杂度：O(n)，其中 n 是数组 reward1  和 reward2  的长度。需要创建长度为 n 的数组 diffs 并排序，数组需要 O(n) 的空间，排序需要 O(logn) 的递归调用栈空间，因此空间复杂度是 O(n)。
        public int miceAndCheese(int[] reward1, int[] reward2, int k) {
            int n = reward1.length, sum = 0, diff[] = new int[n];
            for(int i = 0; i < n ;i++){
                diff[i] = reward2[i] - reward1[i];
                sum += reward2[i];
            }
            Arrays.sort(diff);
            for(int i = 0; i < k; i++){
                sum -= diff[i];
            }
            return sum;
        }
        
        //贪心 + 优先队列
        public int miceAndCheese2(int[] reward1, int[] reward2, int k) {
            int n = reward1.length, sum = 0;
            PriorityQueue<Integer> q = new PriorityQueue<>();
            for(int i = 0; i < n ; i++){
                sum += reward2[i];
                q.offer(reward1[i] - reward2[i]);
                if(q.size() > k){
                    q.poll();
                }                
            }
            while(!q.isEmpty()){
                sum += q.poll();
            }
            return sum;
        }
        
        
    }
    //leetcode submit region end(Prohibit modification and deletion)

}