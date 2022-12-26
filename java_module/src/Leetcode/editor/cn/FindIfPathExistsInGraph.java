package Leetcode.editor.cn;

import java.util.*;

/**
 * 2022-12-19 12:02:41
 * [1971] - 寻找图中是否存在路径
 * FindIfPathExistsInGraph.md
 */
public class FindIfPathExistsInGraph{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new FindIfPathExistsInGraph().new Solution();
        System.out.println("预期结果：true , 运行结果：" + solution.validPath(3, new int[][]{{0,1},{1,2},{2,0}}, 0, 2));
        System.out.println("预期结果：false , 运行结果：" + solution.validPath(6, new int[][]{{0,1}, {0,2}, {3,5}, {5,4}, {4,3}}, 0, 5));
        System.out.println("预期结果：true , 运行结果：" + solution.validPath(1, new int[][]{}, 0, 0));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean validPath(int n, int[][] edges, int source, int destination) {
            HashMap<Integer, List<Integer>> map = new HashMap<>();
            for(int[] edge : edges){
                map.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
                map.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
            }
            
            boolean[] visited = new boolean[n + 1];
            Deque<Integer> queue = new ArrayDeque<>();
            queue.offer(source);
            visited[source] = true;
            while(!queue.isEmpty()){
                int currNode = queue.poll();
                if(currNode == destination){
                    return true;
                }
                for(int nextNode : map.get(currNode)){
                    if(nextNode == destination){
                        return true;
                    }else if(!visited[nextNode]){
                        queue.offer(nextNode);
                        visited[nextNode] = true;
                    }                    
                }                
            }
            return false;            
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}