package Leetcode.editor.cn;

import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

/**
 * 2023-05-24 10:14:32
 * [1377] - T 秒后青蛙的位置
 * FrogPositionAfterTSeconds.md
 */
 
//给你一棵由 n 个顶点组成的无向树，顶点编号从 1 到 n。青蛙从 顶点 1 开始起跳。规则如下： 
// 在一秒内，青蛙从它所在的当前顶点跳到另一个 未访问 过的顶点（如果它们直接相连）。 
// 青蛙无法跳回已经访问过的顶点。 
// 如果青蛙可以跳到多个不同顶点，那么它跳到其中任意一个顶点上的机率都相同。 
// 如果青蛙不能跳到任何未访问过的顶点上，那么它每次跳跃都会停留在原地。 
//
// 无向树的边用数组 edges 描述，其中 edges[i] = [ai, bi] 意味着存在一条直接连通 ai 和 bi 两个顶点的边。 
// 返回青蛙在 t 秒后位于目标顶点 target 上的概率。与实际答案相差不超过 10⁻⁵ 的结果将被视为正确答案。 
//
// 示例 1： 
//输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 2, target = 4
//输出：0.16666666666666666 
//解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，第 1 秒 有 1/3 的概率跳到顶点 2 ，然后第 2 秒 有 1/2 的概率跳到顶点 4，因此青蛙在 2 秒后位于顶点 4 的概率是 1/3 * 1/2 = 1/6 = 0.16666666666666666 。 
//
// 示例 2： 
//输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 1, target = 7
//输出：0.3333333333333333
//解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，有 1/3 = 0.3333333333333333 的概率能够 1 秒 后跳到顶点 7 。 
//
// 提示： 
// 1 <= n <= 100 
// edges.length == n - 1 
// edges[i].length == 2 
// 1 <= ai, bi <= n 
// 1 <= t <= 50 
// 1 <= target <= n 

public class FrogPositionAfterTSeconds{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new FrogPositionAfterTSeconds().new Solution();
        System.out.println("预期结果：0.16666666666666666 , 运行结果：" + solution.frogPosition(7, new int[][]{{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}}, 2, 4));
        System.out.println("预期结果：0.3333333333333333 , 运行结果：" + solution.frogPosition(7, new int[][]{{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}}, 1, 7));
        System.out.println("预期结果：0.0 , 运行结果：" + solution.frogPosition(16, new int[][]{{2,1},{3,1},{4,1},{5,4},{6,3},{7,4},{8,7},{9,5},{10,4},{11,7},{12,3},{13,11},{14,3},{15,13},{16,15}}, 1, 1));
        System.out.println("预期结果：1.0 , 运行结果：" + solution.frogPosition(3, new int[][]{{2,1},{3,2}}, 1, 2));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //复杂度分析
        // 时间复杂度：O(n)，n 是节点数量。
        // 空间复杂度：O(n)，n 是节点数量。
        
        public double frogPosition(int n, int[][] edges, int t, int target) {
            // 构建领接表
            ArrayList<Integer>[] G = new ArrayList[n + 1];
            Arrays.setAll(G, k -> new ArrayList<>());
            for(int[] edge : edges){
                G[edge[0]].add(edge[1]);
                G[edge[1]].add(edge[0]);
            }

            boolean[] visited = new boolean[n + 1];
            Queue<Pair<Integer, Double>> queue = new ArrayDeque<>();
            queue.offer(new Pair<>(1, 1.0));
            visited[1] = true;
            
            while(!queue.isEmpty() && t >= 0){
                for(int i = queue.size(); i > 0; i--){
                    Pair<Integer, Double> node = queue.poll();
                    int u = node.getKey();
                    double p = node.getValue();
                    int cnt = G[u].size() - (u == 1 ? 0 : 1);
                    //System.out.println("u = " + u + " , t = " + t +" , cnt = " +  cnt + " , p = " + p);
                    //如果 u=target，说明青蛙已经到达目标顶点
                    if(u == target){
                        //此时我们判断青蛙是否在 t 秒到达目标顶点，或者不到 t 秒到达目标顶点但是无法再跳跃到其它顶点（即 t=0 或者 cnt=0）。如果是，则返回 p，否则返回 0
                        return cnt * t == 0 ? p : 0;
                    }
                    for(int v : G[u]){
                        if(!visited[v]){
                            visited[v] = true;
                            queue.offer(new Pair<>(v, p / cnt));
                        }
                    }                    
                }
                t--;
            }
            return 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}