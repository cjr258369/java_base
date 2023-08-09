package Leetcode.editor.cn;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 2023-05-23 10:12:46
 * [1090] - 受标签影响的最大值
 * LargestValuesFromLabels.md
 */
 
//我们有一个 n 项的集合。给出两个整数数组 values 和 labels ，第 i 个元素的值和标签分别是 values[i] 和 labels[i]。还会给出两个整数 numWanted 和 useLimit 。 
// 从 n 个元素中选择一个子集 s : 
// 子集 s 的大小 小于或等于 numWanted 。 
// s 中 最多 有相同标签的 useLimit 项。 
//
// 一个子集的 分数 是该子集的值之和。  返回子集 s 的最大 分数 。 
//
// 示例 1： 
//输入：values = [5,4,3,2,1], labels = [1,1,2,2,3], numWanted = 3, useLimit = 1
//输出：9
//解释：选出的子集是第一项，第三项和第五项。
//
// 示例 2： 
//输入：values = [5,4,3,2,1], labels = [1,3,3,3,2], numWanted = 3, useLimit = 2
//输出：12
//解释：选出的子集是第一项，第二项和第三项。
//
// 示例 3： 
//输入：values = [9,8,8,7,6], labels = [0,0,0,1,1], numWanted = 3, useLimit = 1
//输出：16
//解释：选出的子集是第一项和第四项。
//
// 提示： 
// n == values.length == labels.length 
// 1 <= n <= 2 * 10⁴ 
// 0 <= values[i], labels[i] <= 2 * 10⁴ 
// 1 <= numWanted, useLimit <= n 

public class LargestValuesFromLabels{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new LargestValuesFromLabels().new Solution();
        System.out.println("预期结果：9 , 运行结果：" + solution.largestValsFromLabels2(new int[]{5,4,3,2,1}, new int[]{1,1,2,2,3}, 3, 1));
        System.out.println("预期结果：12 , 运行结果：" + solution.largestValsFromLabels2(new int[]{5,4,3,2,1}, new int[]{1,3,3,3,2}, 3, 2));
        System.out.println("预期结果：16 , 运行结果：" + solution.largestValsFromLabels2(new int[]{9,8,8,7,6}, new int[]{0,0,0,1,1}, 3, 1));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //排序取最大值
        //复杂度分析
        // 时间复杂度：O(nlogn)，即为排序需要的时间。后续遍历需要的时间为 O(n)，在渐进意义下小于 O(nlogn)。
        // 空间复杂度：O(n)，即为存储下标的数组以及哈希表需要使用的空间。
        public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
            int ans = 0, usedLabels[] = new int[20005];
            PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[0] - a[0]);
            for(int i = 0; i < values.length; i++){
                queue.offer(new int[]{values[i], labels[i]});
            }
            
            while(numWanted-- > 0 && !queue.isEmpty()){
                while(!queue.isEmpty() && usedLabels[queue.peek()[1]] + 1 > useLimit){
                    queue.poll();
                }
                if(!queue.isEmpty()){
                    int[] t  = queue.poll();
                    ans += t[0];
                    usedLabels[t[1]]++;
                }
            }
            return ans;    
        }
        
        //直接用下标排序
        public int largestValsFromLabels2(int[] values, int[] labels, int numWanted, int useLimit) {
            int ans = 0, usedLabels[] = new int[20005];
            Integer[] ids = new Integer[values.length];
            for(int i = 0; i < values.length; i++){
                ids[i] = i;
            }
            Arrays.sort(ids, (a, b) -> values[b] - values[a]);
            for(int i = 0; i < values.length && numWanted > 0; i++){
                if(usedLabels[labels[ids[i]]] == useLimit){
                    continue;
                }
                ans += values[ids[i]];
                usedLabels[labels[ids[i]]]++;
                numWanted--;
            }
            return ans;    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}