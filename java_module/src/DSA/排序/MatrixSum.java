package DSA.排序;

/**
 * 二维前缀和模板，预处理子矩形的前缀和，以达到快速求二维矩形的某个子矩形的sum
 * @date 2023/8/17
 */
public class MatrixSum {
    private int[][] sum;
    
    //用 lc 1444题的来举例，传入的的是字符串数组，有 'A' 则计算为 1，然后求和
    //同时 'A' 的 ASCII 码最低位为 1，'.' 的 ASCII 码最低位为 0）
    public MatrixSum(String[] matrix){
        int m = matrix.length, n = matrix[0].length();
        sum = new int[m + 1][n + 1];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + (matrix[i].charAt(j) & 1);
            }
        }
    }

    //同样的用 lc 1444题 返回左上角在 (r1,c1) 右下角在 (r2-1,c2-1) 的子矩阵元素和（类似前缀和的左闭右开）
    public int query(int r1, int c1, int r2, int c2){
        return sum[r2][c2] - sum[r2][c1] - sum[r1][c2] + sum[r1][c1];
    }
}
