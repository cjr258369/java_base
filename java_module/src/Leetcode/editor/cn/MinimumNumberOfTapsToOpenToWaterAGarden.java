package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-02-21 09:09:00
 * [1326] - 灌溉花园的最少水龙头数目
 * MinimumNumberOfTapsToOpenToWaterAGarden.md
 */
 
//在 x 轴上有一个一维的花园。花园长度为 n，从点 0 开始，到点 n 结束。 花园里总共有 n + 1 个水龙头，分别位于 [0, 1, ..., n] 。  
//给你一个整数 n 和一个长度为 n + 1 的整数数组 ranges ，其中 ranges[i] （下标从 0 开始）表示：如果打开点 i 处的水龙头，可以灌溉的区域为 [i - ranges[i], i + ranges[i]] 。 
//请你返回可以灌溉整个花园的 最少水龙头数目 。如果花园始终存在无法灌溉到的地方，请你返回 -1 。 
//
// 示例 1： 
//输入：n = 5, ranges = [3,4,1,1,0,0]
//输出：1
//解释：
//点 0 处的水龙头可以灌溉区间 [-3,3]
//点 1 处的水龙头可以灌溉区间 [-3,5]
//点 2 处的水龙头可以灌溉区间 [1,3]
//点 3 处的水龙头可以灌溉区间 [2,4]
//点 4 处的水龙头可以灌溉区间 [4,4]
//点 5 处的水龙头可以灌溉区间 [5,5]
//只需要打开点 1 处的水龙头即可灌溉整个花园 [0,5] 。
//
// 示例 2： 
//输入：n = 3, ranges = [0,0,0,0]
//输出：-1
//解释：即使打开所有水龙头，你也无法灌溉整个花园。
//
// 提示： 
// 1 <= n <= 10⁴ 
// ranges.length == n + 1 
// 0 <= ranges[i] <= 100 

public class MinimumNumberOfTapsToOpenToWaterAGarden{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MinimumNumberOfTapsToOpenToWaterAGarden().new Solution();
        System.out.println("预期结果：1 , 运行结果：" + solution.minTaps(5, new int[]{3,4,1,1,0,0}));
        System.out.println("预期结果：-1 , 运行结果：" + solution.minTaps(3, new int[]{0,0,0,0}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //贪心：判断每个点能去到的最远有边界
        // 复杂度分析
        // 时间复杂度：O(n)，其中 n 表示给定的数字 n。我们需遍历 ranges 数组一遍，然后再遍历 [0,n] 每个位置，因此时间复杂度为 O(n)。
        // 空间复杂度：O(n)，其中 n 表示给定的数字 n。保存每个位置 i∈[0,n] 为起点的子区间的右端点的最大值，需要的空间为 O(n)，因此总的空间复杂度为 O(n)。
        
        //【37 / 37  10 ms   42 MB】
        public int minTaps(int n, int[] ranges) {
            //rightMost：每个左端点，能去到的最远右端点
            //nextPos：下一次可以跳的最远位置
            //currPos：本次可以跳的最远位置
            int rightMost[] = new int[n + 1], nextPos = 0, currPos = 0, ans = 0;
            for(int i = 0; i <= n; i++){
                rightMost[i] = i;
            }
            //预处理每个左端点，能去到的最远右端点
            for(int i = 0; i <= n; i++){
                int start = Math.max(0, i - ranges[i]), end = Math.min(n, i + ranges[i]);
                rightMost[start] = Math.max(rightMost[start], end);
            }
            System.out.println(Arrays.toString(rightMost));
            
            //从头计算每一个端点
            for(int i = 0; i < n; i++){
                nextPos = Math.max(nextPos, rightMost[i]);
                if(i == nextPos){
                    //本次可以跳到的最远位置 无法 覆盖到当前点了，所以直接返回 -1
                    return -1;
                }
                if(i == currPos){
                    ans++;
                    currPos = nextPos;
                }
            }
            return ans;    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}