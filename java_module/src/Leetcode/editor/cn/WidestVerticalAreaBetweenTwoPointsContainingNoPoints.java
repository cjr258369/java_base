package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-03-30 14:14:09
 * [1637] - 两点之间不包含任何点的最宽垂直区域
 * WidestVerticalAreaBetweenTwoPointsContainingNoPoints.md
 */
 
//给你 n 个二维平面上的点 points ，其中 points[i] = [xi, yi] ，请你返回两点之间内部不包含任何点的 最宽垂直区域 的宽度。
// 垂直区域 的定义是固定宽度，而 y 轴上无限延伸的一块区域（也就是高度为无穷大）。 最宽垂直区域 为宽度最大的一个垂直区域。
// 请注意，垂直区域 边上 的点 不在 区域内。 
//
// 示例 1： 
//输入：points = [[8,7],[9,9],[7,4],[9,7]]
//输出：1
//解释：红色区域和蓝色区域都是最优区域。
//
// 示例 2： 
//输入：points = [[3,1],[9,0],[1,0],[1,4],[5,3],[8,8]]
//输出：3
//
// 提示： 
// n == points.length 
// 2 <= n <= 10⁵ 
// points[i].length == 2 
// 0 <= xi, yi <= 10⁹ 

public class WidestVerticalAreaBetweenTwoPointsContainingNoPoints{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new WidestVerticalAreaBetweenTwoPointsContainingNoPoints().new Solution();
        System.out.println("预期结果：1 , 运行结果：" + solution.maxWidthOfVerticalArea2(new int[][]{{8,7},{9,9},{7,4},{9,7}}));
        System.out.println("预期结果：3 , 运行结果：" + solution.maxWidthOfVerticalArea2(new int[][]{{3,1},{9,0},{1,0},{1,4},{5,3},{8,8}}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //排序两点之间内部不包含任何点的最宽垂直面积的宽度，即所有点投影到横轴上后，求相邻的两个点的最大距离。可以先将输入的坐标按照横坐标排序，然后依次求出所有相邻点的横坐标距离，返回最大值。
        //复杂度分析
        // 时间复杂度：O(nlogn)，其中 n 是输入数组的长度，排序消耗 O(nlogn) 时间复杂度。
        // 空间复杂度：O(logn)，为排序的空间复杂度。
        public int maxWidthOfVerticalArea(int[][] points) {
            Arrays.sort(points, (a, b) -> a[0] - b[0]);
            int ans = 0;
            for(int i = 1; i < points.length; i++){
                ans = Math.max(ans, points[i][0] - points[i - 1][0]);
            }
            return ans;    
        }
        
        //效率不高的一行：
        public int maxWidthOfVerticalArea2(int[][] points) {
            return Arrays.stream(points)
                    .sorted((a, b)-> a[0] - b[0])
                    .reduce(new int[]{0x3f3f3f3f, 0}, (a, b) -> new int[]{b[0], Math.max(a[1], b[0] - a[0])})[1];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}