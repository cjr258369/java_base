package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2022-12-01 10:58:33
 * [2482] - 行和列中一和零的差值
 * DifferenceBetweenOnesAndZerosInRowAndColumn.md
 */
public class DifferenceBetweenOnesAndZerosInRowAndColumn{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new DifferenceBetweenOnesAndZerosInRowAndColumn().new Solution();
        System.out.println("预期结果：[[0,0,4],[0,0,4],[-2,-2,2]], 运行结果：" + Arrays.toString(solution.onesMinusZeros(new int[][]{{0,1,1},{1,0,1},{0,0,1}})));
        System.out.println("预期结果：[[5,5,5],[5,5,5]], 运行结果：" + Arrays.toString(solution.onesMinusZeros(new int[][]{{1,1,1},{1,1,1}})));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] onesMinusZeros(int[][] grid) {
            int m = grid.length, n = grid[0].length, or[] = new int[m], oc[] = new int[n];
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    if(grid[i][j] == 1){
                        or[i]++;
                        oc[j]++;
                    }else{
                        or[i]--;
                        oc[j]--;
                    }
                }
            }
            int[][] result = new int[m][n];
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    result[i][j] = or[i] + oc[j];
                }
            }
            return result;
        }
        
        //尽量减少 if  else
        public int[][] onesMinusZeros2(int[][] grid) {
            int m = grid.length, n = grid[0].length, or[] = new int[m], oc[] = new int[n];
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    or[i] += grid[i][j];
                    oc[j] += grid[i][j];
                }
            }
            int[][] result = new int[m][n];
            for(int i = 0; i < m; i++){
                or[i] -= m - or[i];
                for(int j = 0; j < n; j++){
                    result[i][j] = or[i] + oc[j] - (n - oc[j]);
                }
            }
            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}