package Leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 2023-07-21 10:10:01
 * [1499] - 满足不等式的最大值
 * MaxValueOfEquation.md
 */
 
//给你一个数组 points 和一个整数 k 。数组中每个元素都表示二维平面上的点的坐标，并按照横坐标 x 的值从小到大排序。也就是说 points[i] = [xi, yi] ，并且在 1 <= i < j <= points.length 的前提下， xi < xj 总成立。 
// 请你找出 yi + yj + |xi - xj| 的 最大值，其中 |xi - xj| <= k 且 1 <= i < j <= points.length。 
// 题目测试数据保证至少存在一对能够满足 |xi - xj| <= k 的点。 
//
// 示例 1： 
// 输入：points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
//输出：4
//解释：前两个点满足 |xi - xj| <= 1 ，代入方程计算，则得到值 3 + 0 + |1 - 2| = 4 。第三个和第四个点也满足条件，得到值 10 + -10 + |5 - 6| = 1 。
//没有其他满足条件的点，所以返回 4 和 1 中最大的那个。 
//
// 示例 2： 
// 输入：points = [[0,0],[3,0],[9,2]], k = 3
//输出：3
//解释：只有前两个点满足 |xi - xj| <= 3 ，代入方程后得到值 0 + 0 + |0 - 3| = 3 。
//
// 提示： 
// 2 <= points.length <= 10^5 
// points[i].length == 2 
// -10^8 <= points[i][0], points[i][1] <= 10^8 
// 0 <= k <= 2 * 10^8 
// 对于所有的1 <= i < j <= points.length ，points[i][0] < points[j][0] 都成立。也就是说，xi 是严格递增的。 

public class MaxValueOfEquation{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MaxValueOfEquation().new Solution();
        System.out.println("预期结果：4 , 运行结果：" + solution.findMaxValueOfEquation(new int[][]{{1,3},{2,0},{5,10},{6,-10}}, 1));
        System.out.println("预期结果：3 , 运行结果：" + solution.findMaxValueOfEquation(new int[][]{{0,0},{3,0},{9,2}}, 3));
        System.out.println("预期结果：-6 , 运行结果：" + solution.findMaxValueOfEquation(new int[][]{{-19,9},{-15,-19},{-5,-8}}, 10));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //可以把题目给的方程转换一下，因为 xi 是严格递增的，所以：max(yi + yj + |xi - xj|) = max(yi + yj + xj - xi) = max(xj + yj) + max(yi - xi) ， 
        // 同时由于有一个 k 的限制，所以 xj - xi < k。所以使用单调栈时，需要记录一下 xi
        // 首先把队首的超出范围的数据出队，即 xj - xi < k 的数据
        // 然后把（xi，yi - xi）入队，入队前，如果发现队首元素的 yi - xi 不低于队尾的数据，则弹出队首
        // 这样维护后，单调队列的 yi - xi 从队首到队尾都是严格递减的，yi - xi 的最大值即为队首最大值
        
        //复杂度分析
        // 时间复杂度：O(n)，其中 n 为 points 的长度。每个点至多入队出队各一次，所以时间复杂度是 O(n)。
        // 空间复杂度：O(n)。
        public int findMaxValueOfEquation(int[][] points, int k) {
            int ans = Integer.MIN_VALUE;
            ArrayDeque<int[]> queue = new ArrayDeque<>();
            for(int[] point : points){
                // 队首超出范围
                while(!queue.isEmpty() &&  point[0] - queue.peekFirst()[0] > k){
                    queue.pollFirst();                    
                }
                if(!queue.isEmpty()){
                    // 加上最大的 yi-xi
                    ans = Math.max(ans, point[0] + point[1] + queue.peekFirst()[1]);
                }
                // 队尾不如新来的强
                while(!queue.isEmpty() && point[1] - point[0] >= queue.peekLast()[1]){
                    queue.pollLast();
                }
                queue.addLast(new int[]{point[0], point[1] - point[0]});
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}