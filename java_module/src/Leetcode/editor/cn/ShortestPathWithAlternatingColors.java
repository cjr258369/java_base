package Leetcode.editor.cn;

import java.util.*;

/**
 * 2023-02-02 09:23:34
 * [1129] - 颜色交替的最短路径
 * ShortestPathWithAlternatingColors.md
 */
 
//在一个有向图中，节点分别标记为 0, 1, ..., n-1。图中每条边为红色或者蓝色，且存在自环或平行边。 
// red_edges 中的每一个 [i, j] 对表示从节点 i 到节点 j 的红色有向边。类似地，blue_edges 中的每一个 [i, j] 对表示从节点 i 到节点 j 的蓝色有向边。 
// 返回长度为 n 的数组 answer，其中 answer[X] 是从节点 0 到节点 X 的红色边和蓝色边交替出现的最短路径的长度。如果不存在这样的路径，那么 answer[x] = -1。 
//
// 示例 1： 
//输入：n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
//输出：[0,1,-1]
//
// 示例 2： 
//输入：n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
//输出：[0,1,-1]
//
// 示例 3： 
//输入：n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
//输出：[0,-1,-1]
//
// 示例 4： 
//输入：n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
//输出：[0,1,2]
//
// 示例 5： 
//输入：n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
//输出：[0,1,1]
//
// 提示： 
// 1 <= n <= 100 
// red_edges.length <= 400 
// blue_edges.length <= 400 
// red_edges[i].length == blue_edges[i].length == 2 
// 0 <= red_edges[i][j], blue_edges[i][j] < n 

public class ShortestPathWithAlternatingColors{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new ShortestPathWithAlternatingColors().new Solution();
        System.out.println("预期结果：[0,1,-1] , 运行结果：" + Arrays.toString(solution.shortestAlternatingPaths(3, new int[][]{{0, 1}, {1, 2}}, new int[][]{})));
        System.out.println("预期结果：[0,1,-1] , 运行结果：" + Arrays.toString(solution.shortestAlternatingPaths(3, new int[][]{{0, 1}}, new int[][]{{2, 1}})));
        System.out.println("预期结果：[0,-1,-1] , 运行结果：" + Arrays.toString(solution.shortestAlternatingPaths(3, new int[][]{{1, 0}}, new int[][]{{2, 1}})));
        System.out.println("预期结果：[0,1,2] , 运行结果：" + Arrays.toString(solution.shortestAlternatingPaths(3, new int[][]{{0, 1}}, new int[][]{{1, 2}})));
        System.out.println("预期结果：[0,1,1] , 运行结果：" + Arrays.toString(solution.shortestAlternatingPaths(3, new int[][]{{0, 1}, {0,2}}, new int[][]{{1, 0}})));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //最短路径通常使用：广度优先搜索，因为层级即路径，并且是最短的。
        //复杂度分析
        //时间复杂度：O(n+r+b)，其中 n 是节点数，r 是红色边的数目，b 是蓝色边的数目。广度优先搜索最多访问一个节点两次，最多访问一条边一次，因此时间复杂度为 O(n+r+b)。
        //空间复杂度：O(n+r+b)。队列中最多有 2n 个元素，保存 next 需要 O(r+b) 的空间，保存 dist 需要 O(n) 的空间。
        public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
            //构建邻接表存图【0红色，1蓝色】
            List<Integer>[][] next = new List[2][n];
            for(int i = 0; i < 2; i++){
                for(int j = 0; j < n; j++){
                    next[i][j] = new ArrayList<>();
                }
            }
            for(int[] edge : redEdges){
                next[0][edge[0]].add(edge[1]);
            }
            for(int[] edge : blueEdges){
                next[1][edge[0]].add(edge[1]);
            }

            //记录两种类型的颜色最短路径的长度
            int[][] dist = new int[2][n];
            Arrays.fill(dist[0], Integer.MAX_VALUE);
            Arrays.fill(dist[1], Integer.MAX_VALUE);
            
            //开始执行bfs，int[2]：a[0] = 颜色，a[1] = 节点
            Queue<int[]> queue = new ArrayDeque<>();
            dist[0][0] = 0;
            dist[1][0] = 0;
            queue.offer(new int[]{0, 0});
            queue.offer(new int[]{1, 0});
            
            while(!queue.isEmpty()){
                int[] node = queue.poll();
                //搜另外一个颜色的路径
                for(int nextNode : next[1 - node[0]][node[1]]){
                    //如果遍历过了，就不再遍历了
                    if(dist[1 - node[0]][nextNode] != Integer.MAX_VALUE){
                        continue;                        
                    }
                    //否则记录路径，并增加节点
                    dist[1 - node[0]][nextNode] = dist[node[0]][node[1]] + 1;
                    queue.offer(new int[]{1 - node[0], nextNode});                    
                }                
            }
            
            //比较两个颜色的路径，找最短，如果不存在，返回 - 1
            int[] ans = new int[n];
            for(int i = 0; i < n; i++){
                ans[i] = Math.min(dist[0][i], dist[1][i]);
                ans[i] = ans[i] == Integer.MAX_VALUE ? -1 : ans[i];               
            }
            return ans;    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}