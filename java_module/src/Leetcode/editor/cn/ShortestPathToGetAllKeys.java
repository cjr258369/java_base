package Leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Queue;

/**
 * 2022-11-28 16:16:22
 * [864] - 获取所有钥匙的最短路径
 * ShortestPathToGetAllKeys.md
 */
public class ShortestPathToGetAllKeys{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new ShortestPathToGetAllKeys().new Solution();
        System.out.println("预期结果：8 , 运行结果：" + solution.shortestPathAllKeys2(new String[]{"@.a.#","###.#","b.A.B"}));
        System.out.println("预期结果：6 , 运行结果：" + solution.shortestPathAllKeys2(new String[]{"@..aA","..B#.","....b"}));
        System.out.println("预期结果：-1 , 运行结果：" + solution.shortestPathAllKeys2(new String[]{"@Aa"}));
        System.out.println("预期结果：10 , 运行结果：" + solution.shortestPathAllKeys2(new String[]{"@...a",".###A","b.BCc"}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[][] pos = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        public int shortestPathAllKeys(String[] grid) {
            int m = grid.length, n = grid[0].length(), startX = 0, startY = 0;
            HashMap<Character, Integer> keyIdx = new HashMap<>();
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    char ch = grid[i].charAt(j);
                    if('@' == ch){
                        startX = i;
                        startY = j;
                    }else{
                        if(Character.isLowerCase(ch)){
                            if(!keyIdx.containsKey(ch)){
                                keyIdx.put(ch, keyIdx.size());
                            }                            
                        }                        
                    }
                }
            }

            Queue<int[]> queue = new ArrayDeque<>();
            int[][][] dist = new int[m][n][1 << keyIdx.size()];
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    Arrays.fill(dist[i][j], -1);
                }
            }
            
            queue.offer(new int[]{startX, startY, 0});
            dist[startX][startY][0] = 0;
            while(!queue.isEmpty()){
                int[] arr = queue.poll();
                int x = arr[0], y = arr[1], mask = arr[2];
                for(int[] t : pos){
                    int nx = x + t[0], ny = y + t[1];
                    char ch = ' ';
                    //判断边界 ， 以及墙壁
                    if(nx >= 0 && nx < m && ny >= 0 && ny < n && '#' != (ch = grid[nx].charAt(ny))){                        
                        //空房子 或者 起点
                        if('.' == ch || '@' == ch){
                            //未遍历过
                            if(dist[nx][ny][mask] == -1){
                                dist[nx][ny][mask] = dist[x][y][mask] + 1;
                                queue.offer(new int[]{nx, ny, mask});
                            }
                        }else if(Character.isLowerCase(ch)){ /* 钥匙 */
                            int idx = keyIdx.get(ch);
                            //未遍历过
                            if(dist[nx][ny][mask | (1 << idx)] == -1){
                                dist[nx][ny][mask | (1 << idx)] = dist[x][y][mask] + 1;
                                //判断是否所有钥匙都拿到了
                                if((mask | (1 << idx)) == (1 << keyIdx.size()) - 1){
                                    return dist[nx][ny][mask | (1 << idx)];
                                }
                                queue.offer(new int[]{nx, ny, mask | (1 << idx)});
                            }
                        }else{/* 碰到了锁 */
                            int idx = keyIdx.get(Character.toLowerCase(ch));
                            //如果当前已经拿到这把锁的钥匙，且没遍历过
                            if((mask & (1 << idx)) != 0 && dist[nx][ny][mask] == -1){
                                dist[nx][ny][mask] = dist[x][y][mask] + 1;
                                queue.offer(new int[]{nx, ny, mask | (1 << idx)});
                            }
                        }
                    }
                }
            }
            return -1;
        }
        
        //不使用 hashMap，用 a = 97， f = 102 的 int 数组，效率提高
        public int shortestPathAllKeys2(String[] grid) {
            int m = grid.length, n = grid[0].length(), startX = 0, startY = 0, keys = 0, keyIdx[] = new int[103];
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    char ch = grid[i].charAt(j);
                    if('@' == ch){
                        startX = i;
                        startY = j;
                    }else{
                        if(Character.isLowerCase(ch)){
                            if(keyIdx[ch] == 0){
                                keyIdx[ch] = keys++;
                            }                          
                        }                        
                    }
                }
            }

            Queue<int[]> queue = new ArrayDeque<>();
            int[][][] dist = new int[m][n][keys = (1 << keys)];
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    Arrays.fill(dist[i][j], -1);
                }
            }
            
            queue.offer(new int[]{startX, startY, 0});
            dist[startX][startY][0] = 0;
            while(!queue.isEmpty()){
                int[] arr = queue.poll();
                int x = arr[0], y = arr[1], mask = arr[2];
                for(int[] t : pos){
                    int nx = x + t[0], ny = y + t[1];
                    char ch = ' ';
                    //判断边界 ， 以及墙壁
                    if(nx >= 0 && nx < m && ny >= 0 && ny < n && '#' != (ch = grid[nx].charAt(ny))){                        
                        //空房子 或者 起点
                        if('.' == ch || '@' == ch){
                            //未遍历过
                            if(dist[nx][ny][mask] == -1){
                                dist[nx][ny][mask] = dist[x][y][mask] + 1;
                                queue.offer(new int[]{nx, ny, mask});
                            }
                        }else if(Character.isLowerCase(ch)){ /* 钥匙 */
                            int idx = keyIdx[ch];
                            //未遍历过
                            if(dist[nx][ny][mask | (1 << idx)] == -1){
                                dist[nx][ny][mask | (1 << idx)] = dist[x][y][mask] + 1;
                                //判断是否所有钥匙都拿到了
                                if((mask | (1 << idx)) == keys - 1){
                                    return dist[nx][ny][mask | (1 << idx)];
                                }
                                queue.offer(new int[]{nx, ny, mask | (1 << idx)});
                            }
                        }else{/* 碰到了锁 */
                            int idx = keyIdx[Character.toLowerCase(ch)];
                            //如果当前已经拿到这把锁的钥匙，且没遍历过
                            if((mask & (1 << idx)) != 0 && dist[nx][ny][mask] == -1){
                                dist[nx][ny][mask] = dist[x][y][mask] + 1;
                                queue.offer(new int[]{nx, ny, mask | (1 << idx)});
                            }
                        }
                    }
                }
            }
            return -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}